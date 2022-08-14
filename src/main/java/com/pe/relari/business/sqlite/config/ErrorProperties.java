package com.pe.relari.business.sqlite.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Map;

/**
 * <b>Class:</b> ErrorProperties.</br>
 * @author Renzo Lavado Rivas.
 * @version 1.0.0
 */

@Getter
@Setter
@Lazy
@Configuration
@ConfigurationProperties(prefix = "application.backend")
public class ErrorProperties {

  private Map<String, ErrorModel> errors;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ErrorModel {

    private String description;
    private String category;

  }

}
