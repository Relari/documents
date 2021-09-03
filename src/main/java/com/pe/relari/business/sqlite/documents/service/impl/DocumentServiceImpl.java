package com.pe.relari.business.sqlite.documents.service.impl;

import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.service.DocumentService;
import org.springframework.stereotype.Service;

import com.pe.relari.business.sqlite.documents.dao.DocumentDao;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
class DocumentServiceImpl implements DocumentService {

  private final DocumentDao documentDao;

  @Override
  public Flux<Document> documents() {
    return documentDao.findAll();
  }

  @Override
  public Mono<Void> insert(Document document) {
    return documentDao.create(document);
  }

  @Override
  public Mono<Document> findById(Integer id) {
    return documentDao.findById(id);
  }

}
