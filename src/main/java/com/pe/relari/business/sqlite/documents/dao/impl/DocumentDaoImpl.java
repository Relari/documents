package com.pe.relari.business.sqlite.documents.dao.impl;

import com.pe.relari.business.sqlite.documents.dao.DocumentDao;
import com.pe.relari.business.sqlite.documents.dao.repository.DocumentRepository;
import com.pe.relari.business.sqlite.documents.exception.ErrorCategory;
import com.pe.relari.business.sqlite.documents.exception.ErrorFactory;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.model.entity.DocumentEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
class DocumentDaoImpl implements DocumentDao {

  private final DocumentRepository documentRepository;

  private final ErrorFactory errorFactory;

  @Override
  public List<Document> findAll() {
    return documentRepository.findAll()
        .stream()
        .map(this::mapDocument)
        .collect(Collectors.toList());
  }

  @Override
  public void create(Document document) {
    Optional.of(mapDocumentEntity(document))
        .map(documentRepository::save);
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
  public Document findById(Integer id) {
    return documentRepository.findById(id)
        .map(this::mapDocument)
        .orElseThrow(() -> errorFactory.buildException(
            ErrorCategory.EMPLOYEE_NOT_FOUND, null)
    );
  }

  @Override
  public void deleteById(Integer id) {
    documentRepository.deleteById(id);
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
