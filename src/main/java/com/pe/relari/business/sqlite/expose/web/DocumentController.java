package com.pe.relari.business.sqlite.expose.web;

import com.pe.relari.business.sqlite.documents.model.api.CodeResponse;
import com.pe.relari.business.sqlite.documents.model.api.DocumentRequest;
import com.pe.relari.business.sqlite.documents.model.api.DocumentResponse;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.service.DocumentService;
import com.pe.relari.business.sqlite.expose.web.mapper.DocumentMapper;
import com.pe.relari.business.sqlite.expose.web.generate.DocumentsApiDelegate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class DocumentController implements DocumentsApiDelegate {

  private final DocumentService documentService;
  private final DocumentMapper documentMapper;

  @Override
  public ResponseEntity<List<DocumentResponse>> findAllDocumentsUsingGET() {
    List<DocumentResponse> documentResponses = documentService.findAll()
            .stream()
            .map(documentMapper::mapDocumentResponse)
            .collect(Collectors.toList());
    return ResponseEntity.ok(documentResponses);
  }

  @Override
  public ResponseEntity<DocumentResponse> findDocumentByIdUsingGET(Integer id) {
    Document document = documentService.findById(id);
    DocumentResponse documentResponse = documentMapper.mapDocumentResponse(document);
    return ResponseEntity.ok(documentResponse);
  }

  @Override
  public ResponseEntity<Void> deleteDocumentByIdUsingDELETE(Integer id) {
    documentService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<CodeResponse> saveDocumentUsingPOST(DocumentRequest documentRequest) {
    Document document = documentMapper.mapDocument(documentRequest);
    String code = documentService.create(document);

    CodeResponse codeResponse = documentMapper.mapCodeResponse(code);
    return ResponseEntity.ok(codeResponse);
  }

}
