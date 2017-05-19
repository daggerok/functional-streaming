package daggerok;

import daggerok.bindings.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableBinding(Messages.class)
public class StreamingReactiveConsumerApplication {

  final Messages messages;

  @StreamListener(Messages.REACTIVE_MESSAGE)
  public void correctMessagesReceiver(final Flux<Message<String>> payload) {

    payload.map(Message::getPayload)
           .subscribe(message -> log.info("received reactive message: {}", message));
  }

  @StreamListener(Messages.REACTIVE_END)
  public void reactiveEndMessagesReceiver(final Flux<Message<String>> payload) {

    payload.map(Message::getPayload)
           .subscribe(message -> log.info("reactive end: {}", message));
  }

  @StreamListener(Messages.ERR)
  public void errorReceiver(final Flux<Object> payload) {

    payload.subscribe(obj -> log.info("err: {}", obj));
  }

  public static void main(String[] args) {
    SpringApplication.run(StreamingReactiveConsumerApplication.class, args);
  }
}
