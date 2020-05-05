package com.renzo.document.business.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@ApiModel("Model Document")
public class DocumentDto {

  @ApiModelProperty(value = "Id Document", example = "1")
  private Integer ID;

  @ApiModelProperty(value = "Descripcion Document", example = "Document")
  private String descripcion;
}
