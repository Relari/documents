package com.pe.relari.business.sqlite.documents.dao.repository;

import com.pe.relari.business.sqlite.documents.model.entity.DocumentEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DocumentRepository {

    @Select("SELECT * FROM Document")
    @Result(property = "yearPublication", column = "year_publication")
    @Result(property = "numberPages", column = "number_pages")
    List<DocumentEntity> findAll();

    @Select("SELECT * FROM Document WHERE ID = #{id}")
    @Result(property = "yearPublication", column = "year_publication")
    @Result(property = "numberPages", column = "number_pages")
    DocumentEntity findById(int id);

    @Insert("INSERT INTO Document (DESCRIPTION, AUTHOR, GENDER, NUMBER_PAGES, YEAR_PUBLICATION) VALUES (#{description}, #{author}, #{gender}, #{numberPages}, #{yearPublication})")
    int save(DocumentEntity document);

    @Delete("DELETE FROM Document WHERE ID = #{id}")
    void deleteById(int id);

//    @Update("UPDATE Document SET DESCRIPCION = #{descripcion} WHERE ID = #{ID}")
//    void update(DocumentEntity document);

}
