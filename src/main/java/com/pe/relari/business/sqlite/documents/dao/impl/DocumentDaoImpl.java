package com.pe.relari.business.sqlite.documents.dao.impl;

import com.pe.relari.business.sqlite.documents.dao.DocumentDao;
import com.pe.relari.business.sqlite.documents.dao.repository.DocumentRepository;
import com.pe.relari.business.sqlite.documents.exception.DocumentException;
import com.pe.relari.business.sqlite.documents.exception.ErrorCategory;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.model.entity.DocumentEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
class DocumentDaoImpl implements DocumentDao {

  private final DocumentRepository documentRepository;

  @Override
  public List<Document> findAll() {
    log.debug("Listando los documentos.");
    return documentRepository.findAll()
        .stream()
        .map(this::mapDocument)
        .collect(Collectors.toList());
  }

  @Override
  public Integer create(Document document) {
    log.debug("Registrando el nuevo documento.");
    DocumentEntity documentEntity = mapDocumentEntity(document);
    return documentRepository.save(documentEntity);
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
    log.debug("Buscando el documento por el id {}", id);
    DocumentEntity documentEntity = documentRepository.findById(id);
    if (documentEntity == null) {
      throw DocumentException.of(ErrorCategory.EMPLOYEE_NOT_FOUND);
    }
    return this.mapDocument(documentEntity);
  }

  @Override
  public void deleteById(Integer id) {
    log.debug("Eliminando el documento por el id {}", id);
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
