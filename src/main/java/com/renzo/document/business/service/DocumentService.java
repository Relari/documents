package com.renzo.document.business.service;

import com.renzo.document.business.model.dto.DocumentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentService {

  Flux<DocumentDto> documents();

  Mono<Void> insert(DocumentDto documentDto);

}
