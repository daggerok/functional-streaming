package daggerok;

import daggerok.bindings.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableBinding(Messages.class)
public class StreamingReactiveProcessorApplication {

  final Messages messages;

  @Output(Messages.REACTIVE_MESSAGE)
  @StreamListener(Messages.CORRECT_MESSAGES)
  public Flux<Message<String>> correctMessagesReceiver(final Flux<Map<String, String>> payload) {

    return payload.map(Map::entrySet)
                  .map(Set::iterator)
                  .filter(Iterator::hasNext)
                  .map(Iterator::next)
                  .map(i -> format("{\"%s\":\"%s\"}", i.getKey(), i.getValue()))
                  .window(3)
                  .flatMap(w -> w.reduce("|",
                                         (s1, s2) -> s1 + s2 + "|"))
                  .map(body -> MessageBuilder.withPayload(body).build());
  }

  @StreamListener(Messages.INCORRECT_MESSAGES)
  public void incorrectMessagesReceiver(final Flux<Map<String, String>> inbound) {

    inbound.map(Map::entrySet)
           .map(Set::iterator)
           .filter(Iterator::hasNext)
           .map(Iterator::next)
           .map(entry -> "{\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"},")
           .window(Duration.ofSeconds(10), Duration.ofSeconds(5))
           .flatMap(w -> w.reduce(0L, (integer, s) -> integer + 1))
           .subscribe(r -> log.info("{} wrong messages received.", r));
  }

  @Output(Messages.REACTIVE_END)
  @StreamListener(Messages.REACTIVE_START)
  public Flux<Message<String>> reactiveProcessor(final Flux<Message<String>> numbers) {

    return numbers.map(Message::getPayload)
                  .window(Duration.ofSeconds(5))
                  .flatMap(w -> w.reduce("|-> ", (s1, s2) -> s1 + s2 + " -> "))
                  .map(body -> MessageBuilder.withPayload(body)
                                             .build());
  }

  public static void main(String[] args) {
    SpringApplication.run(StreamingReactiveProcessorApplication.class, args);
  }
}
