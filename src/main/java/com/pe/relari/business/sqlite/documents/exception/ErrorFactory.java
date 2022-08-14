package com.pe.relari.business.sqlite.documents.exception;

import com.pe.relari.business.sqlite.config.ErrorProperties;
import com.pe.relari.business.sqlite.documents.model.error.ErrorModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class ErrorFactory {

  private static final List<HttpStatus> HTTP_STATUSES = Arrays.asList(HttpStatus.values());

  private final ErrorProperties errorProperties;

  @ExceptionHandler(DocumentException.class)
  public ResponseEntity<ErrorModel> handleException(DocumentException demoException) {

    ErrorProperties.ErrorModel properties = errorProperties.getErrors()
            .get(demoException.getCatalog().name());

    HttpStatus httpStatus = getHttpStatus(properties.getCategory());

    return ResponseEntity
            .status(httpStatus)
            .body(ErrorModel.builder()
                    .status(httpStatus.value())
                    .message(properties.getDescription())
                    .cause(demoException.getDetail())
                    .build()
            );
  }

  private HttpStatus getHttpStatus(String category) {
    return HTTP_STATUSES.stream()
            .filter(status ->
                    status.name().equals(category)
            )
            .findFirst()
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
