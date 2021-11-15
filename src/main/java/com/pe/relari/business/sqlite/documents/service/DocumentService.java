package com.pe.relari.business.sqlite.documents.service;

import com.pe.relari.business.sqlite.documents.model.domain.Document;

import java.util.List;

public interface DocumentService {

  List<Document> documents();

  void insert(Document document);

  Document findById(Integer id);

  void deleteById(Integer id);

}
