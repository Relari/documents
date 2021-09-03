package com.pe.relari.business.sqlite.documents.service;

import com.pe.relari.business.sqlite.documents.model.domain.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentService {

  Flux<Document> documents();

  Mono<Void> insert(Document document);

  Mono<Document> findById(Integer id);
}