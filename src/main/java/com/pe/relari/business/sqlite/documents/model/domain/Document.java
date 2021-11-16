package com.pe.relari.business.sqlite.documents.model.domain;

import com.pe.relari.business.sqlite.documents.util.DocumentUtil;
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

  public String getDocumentCode() {
    return DocumentUtil.buildDocumentId(id);
  }
}
