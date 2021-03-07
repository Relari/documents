package com.pe.relari.documentsqlite.business.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionFactory {

  private static final String NOT_FOUND = "NOT_FOUND";

  private String message;
  private ErrorCategory errorCategory;
  private Throwable throwable;

  public RuntimeException getException() {
    if (NOT_FOUND.equals(errorCategory.name())) {
        if (throwableIsNull()) {
            return new DocumentNotFoundException(message);
        } else {
            return new DocumentNotFoundException(message, throwable);
        }
    } else {
        if (throwableIsNull()) {
            return new RuntimeException(message);
        } else {
            return new RuntimeException(message, throwable);
        }
    }
  }

  private boolean throwableIsNull() {
    return Objects.isNull(throwable);
  }

  private boolean errors() {
      return categories().stream()
              .anyMatch(s -> s.equals(errorCategory.name()));
  }

  private List<String> categories() {
      return Arrays.stream(ErrorCategory.values())
              .map(Enum::name)
              .collect(Collectors.toList());
  }

}
