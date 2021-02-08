package com.renzo.document.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class DocumentNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DocumentNotFoundException(String message) {
    super(message);
  }

  public DocumentNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
