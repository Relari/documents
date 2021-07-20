package com.pe.relari.expose.web;

import com.pe.relari.documents.model.api.DocumentResponse;
import com.pe.relari.documents.model.domain.Document;
import com.pe.relari.documents.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {

  @Mock
  private DocumentService service;

  @InjectMocks
  private DocumentController controller;

  @Test
  void test() {

    Mockito.when(service.documents())
            .thenReturn(Flux.just(document()));

    StepVerifier.create(controller.listOfDocument())
            .expectNext(documentResponse());
  }

  @Test
  void test1() {

    Mockito.when(service.findById(Mockito.anyInt()))
            .thenReturn(Mono.just(document()));

    StepVerifier.create(controller.findById(1))
            .expectNext(documentResponse());
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
