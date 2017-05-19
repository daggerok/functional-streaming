package daggerok;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.schema.avro.AvroMessageConverterAutoConfiguration;
import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

@Configuration
@EnableSchemaRegistryClient
@EntityScan(basePackages = "daggerok", basePackageClasses = AvroMessageConverterAutoConfiguration.class)
public class AvroConfig {

  @Bean
  public MessageConverter userMessageConverter() {
    return new AvroSchemaMessageConverter(MimeType.valueOf("avro/bytes"));
  }
}
