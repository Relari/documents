package com.pe.relari.documentsqlite.business.dao.repository;

import com.pe.relari.documentsqlite.business.model.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {

}
