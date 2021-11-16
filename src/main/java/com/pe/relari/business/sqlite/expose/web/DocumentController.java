package com.pe.relari.business.sqlite.expose.web;

import com.pe.relari.business.sqlite.documents.model.api.CodeResponse;
import com.pe.relari.business.sqlite.documents.model.api.DocumentRequest;
import com.pe.relari.business.sqlite.documents.model.api.DocumentResponse;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.service.DocumentService;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${application.api.path}")
@AllArgsConstructor
class DocumentController {

  private final DocumentService documentService;

  @GetMapping
  public List<DocumentResponse> findAll() {
    return documentService.findAll()
            .stream()
            .map(this::mapDocumentResponse)
            .collect(Collectors.toList());
  }

  @GetMapping(path = "/{id}")
  public DocumentResponse findById(@PathVariable("id") Integer id) {
    Document document = documentService.findById(id);
    return mapDocumentResponse(document);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable("id") Integer id) {
    documentService.deleteById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CodeResponse create(@RequestBody @Valid DocumentRequest documentRequest) {
    Document document = mapDocument(documentRequest);
    String documentId = documentService.create(document);
    return CodeResponse.of(documentId);
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
            .code(document.getDocumentCode())
            .build();
  }
}
