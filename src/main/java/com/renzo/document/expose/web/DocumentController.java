package com.renzo.document.expose.web;

import com.renzo.document.business.model.api.response.DocumentResponse;
import com.renzo.document.business.model.dto.DocumentDto;
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

@Api(value = "Document")
@RestController
@RequestMapping(path = "/documents")
@AllArgsConstructor
public class DocumentController {

  private DocumentService documentService;

  @ApiOperation(value = "List all documents", notes = "Return all documents",
      produces = "application/json")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list")})
  @GetMapping(path = "")
  public Flux<DocumentResponse> listOfDocument() {
    return documentService.documents().map(this::documentResponse);
  }

  @ApiOperation(value = "Document Maintenance", notes = "Return null",
      consumes = "application/json")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "Document saved successfully.")})
  @PostMapping(path = "")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> insertDocument(@RequestBody DocumentDto documentDto) {
    return documentService.insert(documentDto);
  }

  private DocumentResponse documentResponse(DocumentDto documentDto) {
    return DocumentResponse.builder().document(documentDto.getDescripcion()).build();
  }
}
