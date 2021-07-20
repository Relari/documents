package com.pe.relari.config.database;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.MetadataBuilderInitializer;
import org.hibernate.engine.jdbc.dialect.internal.DialectResolverSet;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;
import org.jboss.logging.Logger;

/**
 * SQLite工具
 */
public class SqLiteMetadataBuilderInitializer implements MetadataBuilderInitializer {

  private final static Logger logger = Logger.getLogger(SqLiteMetadataBuilderInitializer.class);

  @Override
  public void contribute(MetadataBuilder metadataBuilder, StandardServiceRegistry serviceRegistry) {
    DialectResolver dialectResolver = serviceRegistry.getService(DialectResolver.class);

    if (!(dialectResolver instanceof DialectResolverSet)) {
      logger.warnf(
          "DialectResolver '%s' is not an instance of DialectResolverSet, not registering SqLiteDialect",
          dialectResolver);
      return;
    }

    ((DialectResolverSet) dialectResolver).addResolver(resolver);
  }

  static private final SqLiteDialect dialect = new SqLiteDialect();

  static private final DialectResolver resolver = (DialectResolver) info -> {
    if (info.getDatabaseName().equals("database")) {
      return dialect;
    }

    return null;
  };
}
