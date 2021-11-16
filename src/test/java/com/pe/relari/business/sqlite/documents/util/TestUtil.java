package com.pe.relari.business.sqlite.documents.util;

import com.pe.relari.business.sqlite.documents.model.domain.Document;
import com.pe.relari.business.sqlite.documents.model.entity.DocumentEntity;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtil {

    public static Document buildDocument() {
        return Document.builder()
                .id(1)
                .author("author")
                .description("description")
                .gender("gender")
                .numberPages(1)
                .yearPublication(2020)
                .build();
    }

    public static List<Document> buildDocuments() {
        return Collections.singletonList(buildDocument());
    }

    public static DocumentEntity buildDocumentEntity() {
        return DocumentEntity.builder()
                .id(1)
                .author("author")
                .description("description")
                .gender("gender")
                .numberPages(1)
                .yearPublication(2020)
                .build();
    }

    public static List<DocumentEntity> buildDocumentEntities() {
        return Collections.singletonList(buildDocumentEntity());
    }

}
