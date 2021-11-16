package com.pe.relari.business.sqlite.documents.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentUtil {

    public static String buildDocumentId(Integer id) {
        return String.format("DOC%04d", id);
    }

}
