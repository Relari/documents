package com.pe.relari.business.sqlite.documents.exception;

import lombok.Getter;

@Getter
public class DocumentException extends RuntimeException {

    private final ErrorCategory catalog;
    private final Throwable detail;

    protected DocumentException(ErrorCategory catalog) {
        super(null, null);
        this.catalog = catalog;
        this.detail = null;
    }

    protected DocumentException(ErrorCategory catalog, Throwable detail) {
        super(null, null);
        this.catalog = catalog;
        this.detail = detail;
    }

    public static DocumentException of(ErrorCategory catalog) {
        return new DocumentException(catalog);
    }

    public static DocumentException of(ErrorCategory catalog, Throwable throwable) {
        return new DocumentException(catalog, throwable);
    }

}
