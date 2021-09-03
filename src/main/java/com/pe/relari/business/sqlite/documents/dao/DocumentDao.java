package com.pe.relari.business.sqlite.documents.dao;

import com.pe.relari.business.sqlite.documents.model.domain.Document;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentDao {

  Flux<Document> findAll();

  Mono<Void> create(Document document);

  Mono<Document> findById(Integer id);
}
