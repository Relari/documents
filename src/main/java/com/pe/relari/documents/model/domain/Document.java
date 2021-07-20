package com.pe.relari.documents.model.domain;

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
public class Document {

  private Integer id;
  private String author;
  private String description;
  private String gender;
  private Integer yearPublication;
  private Integer numberPages;
}
