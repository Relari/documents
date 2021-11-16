package com.pe.relari.business.sqlite.documents.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequest {

  @NotBlank
  private String author;

  @NotBlank
  private String description;

  @NotBlank
  private String gender;

  @NotNull
  private Integer yearPublication;

  @NotNull
  private Integer numberPages;
}
