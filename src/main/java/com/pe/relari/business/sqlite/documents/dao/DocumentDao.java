package com.pe.relari.business.sqlite.documents.dao;

import com.pe.relari.business.sqlite.documents.model.domain.Document;

import java.util.List;

public interface DocumentDao {

  List<Document> findAll();

  void create(Document document);

  Document findById(Integer id);

  void deleteById(Integer id);
}
