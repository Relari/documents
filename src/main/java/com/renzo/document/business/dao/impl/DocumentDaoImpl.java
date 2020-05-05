package com.renzo.document.business.dao.impl;

import com.renzo.document.business.dao.DocumentDao;
import com.renzo.document.business.dao.repository.DocumentRepository;
import com.renzo.document.business.model.dto.DocumentDto;
import com.renzo.document.business.model.entity.DocumentEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class DocumentDaoImpl implements DocumentDao {

  private DocumentRepository documentRepository;

  @Override
  public Flux<DocumentDto> findAll() {
    return Flux.fromIterable(documentRepository.findAll()).map(this::documentDto)
        .doOnSubscribe(subscription -> log.info("Starting DocumentDaoImpl.findAll method"))
        .doOnComplete(() -> log.info("Ending DocumentDaoImpl.findAll method"));
  }

  @Override
  public Mono create(DocumentDto documentDto) {
    return Mono.fromCallable(() -> documentEntity(documentDto)).map(documentRepository::save)
        .ignoreElement()
        .doOnSubscribe(subscription -> log.info("Starting DocumentDaoImpl.create method"))
        .doOnSuccess(documentEntity -> log.info("Ending DocumentDaoImpl.create method"));
  }

  private DocumentDto documentDto(DocumentEntity documentEntity) {
    return DocumentDto.builder().ID(documentEntity.getId())
        .descripcion(documentEntity.getDescripcion()).build();
  }

  private DocumentEntity documentEntity(DocumentDto documentDto) {
    return DocumentEntity.builder().id(documentDto.getID())
        .descripcion(documentDto.getDescripcion()).build();
  }
}
