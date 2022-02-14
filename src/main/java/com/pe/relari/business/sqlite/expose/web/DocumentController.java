package com.pe.relari.business.sqlite.expose.web;

import com.pe.relari.business.sqlite.documents.model.api.CodeResponse;
import com.pe.relari.business.sqlite.documents.model.api.DocumentRequest;
import com.pe.relari.business.sqlite.documents.model.api.DocumentResponse;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.service.DocumentService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class DocumentController implements DocumentsApiDelegate {

  private final DocumentService documentService;

  @Override
  public ResponseEntity<List<DocumentResponse>> findAllDocumentsUsingGET() {
    List<DocumentResponse> documentResponses = documentService.findAll()
            .stream()
            .map(this::mapDocumentResponse)
            .collect(Collectors.toList());
    return ResponseEntity.ok(documentResponses);
  }

  @Override
  public ResponseEntity<DocumentResponse> findDocumentByIdUsingGET(Integer id) {
    Document document = documentService.findById(id);
    DocumentResponse documentResponse = mapDocumentResponse(document);
    return ResponseEntity.ok(documentResponse);
  }

  @Override
  public ResponseEntity<Void> deleteDocumentByIdUsingDELETE(Integer id) {
    documentService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<CodeResponse> saveDocumentUsingPOST(DocumentRequest documentRequest) {
    Document document = mapDocument(documentRequest);
    String code = documentService.create(document);

    CodeResponse codeResponse = new CodeResponse();
    codeResponse.documentCode(code);
    return ResponseEntity.ok(codeResponse);
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
    DocumentResponse documentResponse = new DocumentResponse();
    documentResponse.setAuthor(document.getAuthor())           ;
    documentResponse.setDescription(document.getDescription());
    documentResponse.setGender(document.getGender());
    documentResponse.setNumberPages(document.getNumberPages());
    documentResponse.setYearPublication(document.getYearPublication());
    documentResponse.setCode(document.getDocumentCode());
    return documentResponse;
  }
}
