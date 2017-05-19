package daggerok;

import daggerok.bindings.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import java.util.Map;

import static daggerok.StreamingSupport.pairs;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableBinding(Messages.class)
public class StreamingProcessorApplication {

  final Messages messages;

  @StreamListener(Messages.MESSAGES)
  public void messagesReceiver(final Message<Map<String, String>> message) {

    val item = message.getPayload().entrySet().iterator().next();

    boolean isCorrect = pairs.stream()
                             .filter(pair -> pair.getKey().equals(item.getKey()))
                             .filter(pair -> pair.getValue().equals(item.getValue()))
                             .count() > 0;

    log.info("processing {} message", isCorrect ? messages.correctMessages() : messages.incorrectMessages());

    if (isCorrect) messages.correctMessages().send(message);
    else messages.incorrectMessages().send(message);
  }

  public static void main(String[] args) {
    SpringApplication.run(StreamingProcessorApplication.class, args);
  }
}
