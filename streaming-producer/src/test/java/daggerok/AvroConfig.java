package daggerok;

import org.springframework.cloud.stream.schema.avro.AvroSchemaMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeType;

@Configuration
public class AvroConfig {

  @Bean
  public MessageConverter userMessageConverter() {
    return new AvroSchemaMessageConverter(MimeType.valueOf("avro/bytes"));
  }
}
