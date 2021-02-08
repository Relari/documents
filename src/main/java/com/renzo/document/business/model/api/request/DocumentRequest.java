package com.renzo.document.business.model.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("DocumentRequest")
public class DocumentRequest {

  @NotBlank
  @ApiModelProperty(
          value = "Author",
          dataType = "String",
          required = true,
          example = "Robert")
  private String author;

  @NotBlank
  @ApiModelProperty(
          value = "Description Document",
          dataType = "String",
          required = true,
          example = "Document")
  private String description;

  @NotBlank
  @ApiModelProperty(
          value = "Gender Document",
          dataType = "String",
          required = true,
          example = "Romantic")
  private String gender;

  @NotNull
  @ApiModelProperty(
          value = "Year Of Publication",
          dataType = "String",
          required = true,
          example = "2020")
  private Integer yearPublication;

  @NotNull
  @ApiModelProperty(
          value = "Number Of Pages",
          dataType = "Integer",
          required = true,
          example = "50")
  private Integer numberPages;
}
