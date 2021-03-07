package com.pe.relari.documentsqlite.business.dao;

import com.pe.relari.documentsqlite.business.model.domain.Document;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentDao {

  Flux<Document> findAll();

  Mono<Void> create(Document document);

  Mono<Document> findById(Integer id);
}
