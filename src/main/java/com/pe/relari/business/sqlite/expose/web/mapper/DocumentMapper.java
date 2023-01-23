package com.pe.relari.business.sqlite.expose.web.mapper;

import com.pe.relari.business.sqlite.documents.model.api.CodeResponse;
import com.pe.relari.business.sqlite.documents.model.api.DocumentRequest;
import com.pe.relari.business.sqlite.documents.model.api.DocumentResponse;
import com.pe.relari.business.sqlite.documents.model.domain.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

  @Mapping(target = "id", ignore = true)
  Document mapDocument(DocumentRequest documentRequest);

  @Mapping(source = "documentCode", target = "code")
  DocumentResponse mapDocumentResponse(Document document);

  @Mapping(target = "documentCode", source = "code")
  CodeResponse mapCodeResponse(String code);

}
