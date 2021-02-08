package com.renzo.document.business.dao.impl;

import com.renzo.document.business.dao.DocumentDao;
import com.renzo.document.business.dao.repository.DocumentRepository;
import com.renzo.document.business.model.domain.Document;
import com.renzo.document.business.model.entity.DocumentEntity;
import com.renzo.document.config.ApplicationProperties;
import com.renzo.document.business.exception.ErrorCategory;
import com.renzo.document.business.exception.ExceptionFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Component
@AllArgsConstructor
class DocumentDaoImpl implements DocumentDao {

  private final DocumentRepository documentRepository;

  private final ApplicationProperties applicationProperties;

  @Override
  public Flux<Document> findAll() {
    return Flux.fromIterable(documentRepository.findAll())
        .map(this::mapDocument)
        .doOnSubscribe(subscription -> log.debug("Listing the documents."))
        .doOnNext(document -> log.trace(document.toString()))
        .doOnComplete(() -> log.info("Obtained documents."));
  }

  @Override
  public Mono<Void> create(Document document) {
    return Mono.fromCallable(() -> mapDocumentEntity(document))
        .map(documentRepository::save)
        .subscribeOn(Schedulers.immediate())
        .doOnSubscribe(subscription -> log.debug("Creating document."))
        .doOnNext(documentEntity -> log.trace(documentEntity.toString()))
        .doOnSuccess(documentEntity -> log.info("Complete creation."))
        .then();
  }

  private DocumentEntity mapDocumentEntity(Document document) {
    return DocumentEntity.builder()
            .author(document.getAuthor())
            .description(document.getDescription())
            .gender(document.getGender())
            .numberPages(document.getNumberPages())
            .yearPublication(document.getYearPublication())
            .id(document.getId())
            .build();
  }

  @Override
  public Mono<Document> findById(Integer id) {
    return Mono.fromCallable(() -> findBy(id))
        .map(this::mapDocument)
        .doOnError(throwable -> log.error("Error searching for a document.", throwable))
        .doOnSubscribe(subscription -> log.debug("Searching for the document by id."))
        .doOnSuccess(documentEntity -> log.info("The searched id was found."));
  }

  private DocumentEntity findBy(Integer id) {
    return documentRepository.findById(id)
        .orElseThrow(() -> ExceptionFactory.builder()
                .message(applicationProperties.getFindDocument())
                .errorCategory(ErrorCategory.NOT_FOUND)
                .build()
                .getException());
  }

  private Document mapDocument(DocumentEntity documentEntity) {
    return Document.builder()
            .author(documentEntity.getAuthor())
            .description(documentEntity.getDescription())
            .gender(documentEntity.getGender())
            .numberPages(documentEntity.getNumberPages())
            .yearPublication(documentEntity.getYearPublication())
            .id(documentEntity.getId())
            .build();
  }

}
