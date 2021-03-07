package com.pe.relari.documentsqlite.business.service.impl;

import org.springframework.stereotype.Service;

import com.pe.relari.documentsqlite.business.dao.DocumentDao;
import com.pe.relari.documentsqlite.business.model.domain.Document;
import com.pe.relari.documentsqlite.business.service.DocumentService;

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
