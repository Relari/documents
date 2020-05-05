package com.renzo.document.business.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.renzo.document.business.dao.DocumentDao;
import com.renzo.document.business.model.dto.DocumentDto;
import com.renzo.document.business.service.DocumentService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

  private DocumentDao documentDao;

  @Override
  public Flux<DocumentDto> documents() {
    return documentDao.findAll()
        .doOnSubscribe(subscription -> log.info("Starting DocumentServiceImpl.documents method"))
        .doOnComplete(() -> log.info("Ending DocumentServiceImpl.documents method"));
  }

  @Override
  public Mono<Void> insert(DocumentDto documentDto) {
    return documentDao.create(documentDto)
        .doOnSubscribe(subscription -> log.info("Starting DocumentServiceImpl.insert method"))
        .doOnSuccess(o -> log.info("Ending DocumentServiceImpl.insert method"));
  }

}
