package com.renzo.document.business.dao;

import com.renzo.document.business.model.dto.DocumentDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentDao {

  Flux<DocumentDto> findAll();

  Mono<Void> create(DocumentDto documentDto);
}
