package com.pe.relari.business.sqlite.documents.dao.repository;

import com.pe.relari.business.sqlite.documents.model.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {

}
