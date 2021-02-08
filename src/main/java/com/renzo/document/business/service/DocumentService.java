package com.renzo.document.business.service;

import com.renzo.document.business.model.domain.Document;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocumentService {

  Flux<Document> documents();

  Mono<Void> insert(Document document);

  Mono<Document> findById(Integer id);
}
