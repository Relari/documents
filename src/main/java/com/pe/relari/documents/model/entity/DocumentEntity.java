package com.pe.relari.documents.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

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
@Entity
@Table(name = "DOCUMENT")
public class DocumentEntity {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "AUTHOR", length = 50, nullable = false)
  private String author;

  @Column(name = "DESCRIPTION", length = 50, nullable = false)
  private String description;

  @Column(name = "GENDER", length = 50, nullable = false)
  private String gender;

  @Column(name = "YEAR_PUBLICATION", nullable = false)
  private Integer yearPublication;

  @Column(name = "NUMBER_PAGES", nullable = false)
  private Integer numberPages;

}
