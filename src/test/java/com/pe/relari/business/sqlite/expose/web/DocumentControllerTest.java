package com.pe.relari.business.sqlite.expose.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

import com.pe.relari.business.sqlite.documents.model.api.CodeResponse;
import com.pe.relari.business.sqlite.documents.model.api.DocumentRequest;
import com.pe.relari.business.sqlite.documents.util.DocumentUtil;
import com.pe.relari.business.sqlite.documents.util.TestUtil;
import com.pe.relari.business.sqlite.documents.model.api.DocumentResponse;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.service.DocumentService;
import com.pe.relari.business.sqlite.expose.web.mapper.DocumentMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {

  @Mock
  private DocumentService service;

  @Mock
  private DocumentMapper documentMapper;

  @InjectMocks
  private DocumentController controller;

  @Test
  void whenGetAllDocumentsThenReturnDocuments() {

    when(service.findAll())
            .thenReturn(TestUtil.buildDocuments());

    when(documentMapper.mapDocumentResponse(any()))
            .thenReturn(buildDocumentResponse());

    List<DocumentResponse> documentResponses = controller.findAllDocumentsUsingGET().getBody();

    assertEquals(1, documentResponses.size());
    assertNotNull(documentResponses);
  }

  @Test
  void whenFindDocumentByIdThenReturnDocument() {

    Document document = TestUtil.buildDocument();

    when(service.findById(anyInt()))
            .thenReturn(document);

    when(documentMapper.mapDocumentResponse(any()))
            .thenReturn(buildDocumentResponse());

    DocumentResponse documentResponse =
            controller.findDocumentByIdUsingGET(document.getId()).getBody();

    assertEquals(document.getAuthor(), documentResponse.getAuthor());
    assertEquals(document.getDescription(), documentResponse.getDescription());
    assertEquals(document.getGender(), documentResponse.getGender());
    assertEquals(document.getNumberPages(), documentResponse.getNumberPages());
    assertEquals(document.getYearPublication(), documentResponse.getYearPublication());
  }

  @Test
  void whenSaveDocumentThenReturnSuccessful() {

    Document document = TestUtil.buildDocument();
    String documentId = DocumentUtil.buildDocumentId(document.getId());

    when(documentMapper.mapDocument(any()))
            .thenReturn(TestUtil.buildDocument());

    when(service.create(any()))
            .thenReturn(documentId);

    when(documentMapper.mapCodeResponse(anyString()))
            .thenReturn(buildCodeResponse());

    CodeResponse codeResponse = controller.saveDocumentUsingPOST(buildDocumentRequest()).getBody();

    assertEquals(documentId, codeResponse.getDocumentCode());
  }

  @Test
  void whenDeleteDocumentByIdThenReturnDocuments() {

    service.deleteById(anyInt());

    ResponseEntity<Void> responseEntity = controller.deleteDocumentByIdUsingDELETE(1);

    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }

  private DocumentRequest buildDocumentRequest() {
    DocumentRequest documentRequest = new DocumentRequest();
    documentRequest.setAuthor("author");
    documentRequest.setDescription("description");
    documentRequest.setGender("gender");
    documentRequest.setNumberPages(1);
    documentRequest.setYearPublication(2020);
    return documentRequest;
  }

  private DocumentResponse buildDocumentResponse() {
    DocumentResponse documentResponse = new DocumentResponse();
    documentResponse.setAuthor("author");
    documentResponse.setDescription("description");
    documentResponse.setGender("gender");
    documentResponse.setNumberPages(1);
    documentResponse.setYearPublication(2020);
    return documentResponse;
  }

  private CodeResponse buildCodeResponse() {
    CodeResponse codeResponse = new CodeResponse();
    codeResponse.documentCode("DOC0001");
    return codeResponse;
  }
}
