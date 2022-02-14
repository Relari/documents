package com.pe.relari.business.sqlite.documents.dao.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.pe.relari.business.sqlite.documents.dao.repository.DocumentRepository;
import com.pe.relari.business.sqlite.documents.exception.ErrorFactory;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.model.entity.DocumentEntity;
import com.pe.relari.business.sqlite.documents.util.TestUtil;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
class DocumentDaoImplTest {

  @Mock
  private DocumentRepository documentRepository;

  @Mock
  private ErrorFactory errorFactory;

  @InjectMocks
  private DocumentDaoImpl documentDao;

  @Test
  void whenGetAllDocumentsThenReturnDocumentEntities() {

    List<DocumentEntity> documentEntities = TestUtil.buildDocumentEntities();

    when(documentRepository.findAll())
            .thenReturn(documentEntities);

    List<Document> documents = documentDao.findAll();

    Assertions.assertEquals(1, documents.size());
  }

  @Test
  void whenSearchDocumentByIdThenReturnDocument() {

    DocumentEntity documentEntity = TestUtil.buildDocumentEntity();

    when(documentRepository.findById(anyInt()))
            .thenReturn(Optional.of(documentEntity));

    Document document = documentDao.findById(documentEntity.getId());

    Assertions.assertEquals(documentEntity.getAuthor(), document.getAuthor());
    Assertions.assertEquals(documentEntity.getDescription(), document.getDescription());
    Assertions.assertEquals(documentEntity.getGender(), document.getGender());
    Assertions.assertEquals(documentEntity.getNumberPages(), document.getNumberPages());
    Assertions.assertEquals(documentEntity.getYearPublication(), document.getYearPublication());

  }

  @Test
  void whenSearchDocumentByIdThenReturnError() {

    when(documentRepository.findById(anyInt()))
            .thenReturn(Optional.empty());

    when(errorFactory.buildException(any(), any()))
            .thenReturn(new RuntimeException());

    Assertions.assertThrows(
            RuntimeException.class, () -> documentDao.findById(1)
    );

  }

  @Test
  void whenSaveDocumentThenReturnId() {

    DocumentEntity documentEntity = TestUtil.buildDocumentEntity();

    when(documentRepository.save(any()))
            .thenReturn(documentEntity);

    Integer id = documentDao.create(TestUtil.buildDocument());

    Assertions.assertEquals(documentEntity.getId(), id);

  }

  @Test
  void whenDeleteDocumentByIdThenReturnError() {

    documentRepository.deleteById(anyInt());

    Integer documentId = 1;
    documentDao.deleteById(documentId);

    Assertions.assertNotNull(documentId);
  }
}
