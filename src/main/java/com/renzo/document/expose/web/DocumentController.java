package com.renzo.document.expose.web;

import com.renzo.document.business.model.api.request.DocumentRequest;
import com.renzo.document.business.model.api.response.DocumentResponse;
import com.renzo.document.business.model.domain.Document;
import com.renzo.document.business.model.entity.DocumentEntity;
import com.renzo.document.business.service.DocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Api(value = "Document")
@RestController
@RequestMapping(path = "${application.api.path}")
@AllArgsConstructor
class DocumentController {

  private final DocumentService documentService;

  @ApiOperation(
          value = "List all documents",
          notes = "Return all documents",
          produces = "application/json")
  @ApiResponses(value = {
          @ApiResponse(
                  code = 200,
                  message = "Successfully retrieved list")})
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

  @ApiOperation(
          value = "Document Maintenance",
          notes = "Return null",
          consumes = "application/json")
  @ApiResponses(value = {
          @ApiResponse(
                  code = 201,
                  message = "Document saved successfully.")})
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
