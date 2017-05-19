package daggerok;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@SpringBootApplication
@RequiredArgsConstructor
@EnableBinding(Source.class)
public class StreamingReactiveProducerApplication {

  @Component
  @RequiredArgsConstructor
  public static class Sender {

    final MessageChannel output;

    public void startSending() {
      /*
      output.send(MessageBuilder.withPayload("up and running...")
                                .build());
      */
      Flux.interval(Duration.ofSeconds(1))
          .subscribe(s -> output.send(MessageBuilder.withPayload("" + new Random().nextInt(20))
                                                    .build()));
    }
  }

  @Bean
  public CommandLineRunner produce(final Sender sender) {
    return args -> sender.startSending();
  }

  public static void main(String[] args) {
    SpringApplication.run(StreamingReactiveProducerApplication.class, args);
  }
}
