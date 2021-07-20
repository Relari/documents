package com.pe.relari.documents.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponse {

  private Integer id;

  private String author;

  private String description;

  private String gender;

  private Integer yearPublication;

  private Integer numberPages;
}
