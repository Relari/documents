package com.pe.relari.business.sqlite.expose.web;

import com.pe.relari.business.sqlite.documents.model.api.DocumentRequest;
import com.pe.relari.business.sqlite.documents.model.api.DocumentResponse;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "${application.api.path}")
@AllArgsConstructor
class DocumentController {

  private final DocumentService documentService;

  @GetMapping
  public Flux<DocumentResponse> listOfDocument() {
    return documentService.documents()
            .map(this::mapDocumentResponse);
  }

  @GetMapping(path = "/{id}")
  public Mono<DocumentResponse> findById(@PathVariable("id") Integer id) {
    return documentService.findById(id)
            .map(this::mapDocumentResponse);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> insertDocument(@RequestBody @Valid DocumentRequest documentRequest) {
    return Mono.fromCallable(() -> mapDocument(documentRequest))
            .flatMap(documentService::insert);
  }

  private Document mapDocument(DocumentRequest documentRequest) {
    return Document.builder()
            .author(documentRequest.getAuthor())
            .description(documentRequest.getDescription())
            .gender(documentRequest.getGender())
            .numberPages(documentRequest.getNumberPages())
            .yearPublication(documentRequest.getYearPublication())
            .build();
  }

  private DocumentResponse mapDocumentResponse(Document document) {
    return DocumentResponse.builder()
            .author(document.getAuthor())
            .description(document.getDescription())
            .gender(document.getGender())
            .numberPages(document.getNumberPages())
            .yearPublication(document.getYearPublication())
            .id(document.getId())
            .build();
  }
}
