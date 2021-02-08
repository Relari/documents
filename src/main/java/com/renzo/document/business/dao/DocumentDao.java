package com.renzo.document.business.dao;

import com.renzo.document.business.model.domain.Document;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentDao {

  Flux<Document> findAll();

  Mono<Void> create(Document document);

  Mono<Document> findById(Integer id);
}
