package com.pe.relari.documentsqlite.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Setter
@Getter
@Lazy
@Configuration
@ConfigurationProperties(prefix = "application.error.message")
public class ApplicationProperties {
    
  private String saveDocument;
  private String findDocument;
}
