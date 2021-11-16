package com.pe.relari.business.sqlite.expose.web;

import com.pe.relari.business.sqlite.documents.model.api.CodeResponse;
import com.pe.relari.business.sqlite.documents.model.api.DocumentRequest;
import com.pe.relari.business.sqlite.documents.util.DocumentUtil;
import com.pe.relari.business.sqlite.documents.util.TestUtil;
import com.pe.relari.business.sqlite.documents.model.api.DocumentResponse;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.service.DocumentService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {

  @Mock
  private DocumentService service;

  @InjectMocks
  private DocumentController controller;

  @Test
  void whenGetAllDocumentsThenReturnDocuments() {

    Mockito.when(service.findAll())
            .thenReturn(TestUtil.buildDocuments());

    List<DocumentResponse> documentResponses = controller.findAll();

    Assertions.assertEquals(1, documentResponses.size());
    Assertions.assertNotNull(documentResponses);
  }

  @Test
  void whenFindDocumentByIdThenReturnDocument() {

    Document document = TestUtil.buildDocument();

    Mockito.when(service.findById(Mockito.anyInt()))
            .thenReturn(document);

    DocumentResponse documentResponse = controller.findById(document.getId());

    Assertions.assertEquals(document.getAuthor(), documentResponse.getAuthor());
    Assertions.assertEquals(document.getDescription(), documentResponse.getDescription());
    Assertions.assertEquals(document.getGender(), documentResponse.getGender());
    Assertions.assertEquals(document.getNumberPages(), documentResponse.getNumberPages());
    Assertions.assertEquals(document.getYearPublication(), documentResponse.getYearPublication());
  }

  @Test
  void whenSaveDocumentThenReturnSuccessful() {

    Document document = TestUtil.buildDocument();
    String documentId = DocumentUtil.buildDocumentId(document.getId());

    Mockito.when(service.create(Mockito.any()))
            .thenReturn(documentId);

    CodeResponse codeResponse = controller.create(buildDocumentRequest());

    Assertions.assertEquals(documentId, codeResponse.getDocumentCode());
  }

  private DocumentRequest buildDocumentRequest() {
    return DocumentRequest.builder()
            .author("author")
            .description("description")
            .gender("gender")
            .numberPages(1)
            .yearPublication(2020)
            .build();
  }
}
