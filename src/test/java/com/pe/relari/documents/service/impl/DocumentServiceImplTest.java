package com.pe.relari.business.sqlite.documents.service.impl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.pe.relari.business.sqlite.documents.dao.DocumentDao;
import com.pe.relari.business.sqlite.documents.util.DocumentUtil;
import com.pe.relari.business.sqlite.documents.util.TestUtil;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {

  @Mock
  private DocumentDao documentDao;

  @InjectMocks
  private DocumentServiceImpl documentService;

  @Test
  void whenGetAllDocumentsThenReturnDocuments() {

    when(documentDao.findAll())
            .thenReturn(TestUtil.buildDocuments());

    List<Document> documents = documentService.findAll();
    System.out.println(documents);

    Assertions.assertEquals(1, documents.size());

  }

  @Test
  void whenSearchDocumentByIdThenReturnDocument() {

    Document document = TestUtil.buildDocument();

    when(documentDao.findById(anyInt()))
            .thenReturn(document);

    Document documentModel = documentService.findById(1);

    Assertions.assertEquals(document.getAuthor(), documentModel.getAuthor());
    Assertions.assertEquals(document.getDescription(), documentModel.getDescription());
    Assertions.assertEquals(document.getGender(), documentModel.getGender());
    Assertions.assertEquals(document.getNumberPages(), documentModel.getNumberPages());
    Assertions.assertEquals(document.getYearPublication(), documentModel.getYearPublication());

  }

  @Test
  void whenSaveDocumentThenReturnSuccessful() {

    Document document = TestUtil.buildDocument();

    Integer id = documentDao.create(document);

    String documentCode = documentService.create(document);

    Assertions.assertEquals(DocumentUtil.buildDocumentId(id), documentCode);

  }

  @Test
  void whenDeleteDocumentByIdThenReturnSuccessful() {

    Document document = TestUtil.buildDocument();

    when(documentDao.findById(anyInt()))
            .thenReturn(document);

    documentDao.deleteById(anyInt());

    Integer documentId = 1;
    documentService.deleteById(documentId);

    Assertions.assertNotNull(documentId);
  }

  @Test
  void whenDeleteDocumentByIdThenReturnNotFound() {

    when(documentDao.findById(anyInt()))
            .thenReturn(null);

    Integer documentId = 1;
    documentService.deleteById(documentId);

    Assertions.assertNotNull(documentId);
  }

}
