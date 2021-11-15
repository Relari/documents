package com.pe.relari.business.sqlite.expose.web;

import com.pe.relari.business.sqlite.documents.model.api.DocumentResponse;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.service.DocumentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {

  @Mock
  private DocumentService service;

  @InjectMocks
  private DocumentController controller;

  @Test
  void whenGetAllDocumentsThenReturnDocuments() {

    Mockito.when(service.documents())
            .thenReturn(Collections.singletonList(document()));

    List<DocumentResponse> documentResponses = controller.listOfDocument();

    Assertions.assertNotNull(documentResponses);
  }

  @Test
  void whenSaveDocumentThenReturnSuccessful() {

    Document document = document();

    Mockito.when(service.findById(Mockito.anyInt()))
            .thenReturn(document);

    DocumentResponse documentResponse = controller.findById(1);

    Assertions.assertEquals(document.getAuthor(), documentResponse.getAuthor());
    Assertions.assertEquals(document.getDescription(), documentResponse.getDescription());
    Assertions.assertEquals(document.getGender(), documentResponse.getGender());
    Assertions.assertEquals(document.getNumberPages(), documentResponse.getNumberPages());
    Assertions.assertEquals(document.getYearPublication(), documentResponse.getYearPublication());
  }

  private Document document() {
    return Document.builder()
            .author("author")
            .description("description")
            .gender("gender")
            .numberPages(1)
            .yearPublication(2020)
            .build();
  }

  private DocumentResponse documentResponse() {
    return DocumentResponse.builder()
            .author("author")
            .description("description")
            .gender("gender")
            .numberPages(1)
            .yearPublication(2020)
            .build();
  }
}
