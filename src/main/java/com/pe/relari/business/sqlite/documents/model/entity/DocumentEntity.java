package com.pe.relari.business.sqlite.documents.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {

  private Integer id;
  private String author;
  private String description;
  private String gender;
  private Integer yearPublication;
  private Integer numberPages;

}
