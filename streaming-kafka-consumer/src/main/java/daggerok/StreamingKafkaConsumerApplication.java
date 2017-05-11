package daggerok;

import daggerok.bindings.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.util.Map;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableBinding(Messages.class)
public class StreamingKafkaConsumerApplication {

  final Messages messages;

  @ServiceActivator(inputChannel = Messages.CORRECT_MESSAGES)
  public void correctReceiver(final Message<Map<String, String>> correctMessage) {
    log.info("received correct: {}", correctMessage.getPayload());
  }

  @ServiceActivator(inputChannel = Messages.INCORRECT_MESSAGES)
  public void incorrectReceiver(final Message<Map<String, String>> incorrectMessage) {
    log.info("received incorrect: {}", incorrectMessage.getPayload());
  }

  public static void main(String[] args) {
    SpringApplication.run(StreamingKafkaConsumerApplication.class, args);
  }
}
