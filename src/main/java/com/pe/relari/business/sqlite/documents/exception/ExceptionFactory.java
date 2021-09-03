package com.pe.relari.business.sqlite.documents.exception;

import com.pe.relari.business.sqlite.config.ErrorModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

/**
 * <b>Class:</b> ExceptionFactory.</br>
 * @author Renzo Lavado Rivas.
 * @version 1.0.0
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionFactory {

  private static final List<HttpStatus> HTTP_STATUSES = Arrays.asList(HttpStatus.values());

  public static ResponseStatusException getException(ErrorModel errorModel, Throwable throwable) {

    HttpStatus status = HTTP_STATUSES.stream()
            .filter(httpStatus -> httpStatus.name().equals(errorModel.getCategory()))
            .findFirst()
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

    return new ResponseStatusException(status, errorModel.getDescription(), throwable);

  }

}
