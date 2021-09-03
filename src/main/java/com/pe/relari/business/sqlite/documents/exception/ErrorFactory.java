package com.pe.relari.business.sqlite.documents.exception;

import com.pe.relari.business.sqlite.config.ErrorModel;
import com.pe.relari.business.sqlite.config.ErrorProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * <b>Class:</b> ErrorFactory.</br>
 * @author Renzo Lavado Rivas.
 * @version 1.0.0
 */

@Component
@AllArgsConstructor
public class ErrorFactory {

  private static final List<ErrorCategory> EXCEPTION_CATEGORIES = Arrays.asList(ErrorCategory.values());

  private final ErrorProperties properties;

  public RuntimeException buildException(ErrorCategory errorCategory, Throwable throwable) {

    ErrorModel errorModel = properties.getErrors().get(errorCategory.name());

    return EXCEPTION_CATEGORIES.stream()
            .filter(exceptionCategory -> exceptionCategory.name().equals(errorModel.getCategory()))
            .findFirst()
            .map(exceptionCategory ->
                    ExceptionFactory.getException(errorModel, throwable)
            )
            .orElse(ExceptionFactory.getException(errorModel, throwable));
  }

}
