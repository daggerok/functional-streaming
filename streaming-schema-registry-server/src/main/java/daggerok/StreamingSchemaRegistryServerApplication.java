package daggerok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.schema.server.EnableSchemaRegistryServer;

@SpringBootApplication
@EnableSchemaRegistryServer
public class StreamingSchemaRegistryServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(StreamingSchemaRegistryServerApplication.class, args);
  }
}
