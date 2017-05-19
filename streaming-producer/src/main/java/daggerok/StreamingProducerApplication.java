package daggerok;

import daggerok.bindings.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

import static daggerok.StreamingSupport.genKey;
import static daggerok.StreamingSupport.genValue;
import static java.util.Collections.singletonMap;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableBinding(Messages.class)
public class StreamingProducerApplication {

  final Messages messages;

  @InboundChannelAdapter(
      value = Messages.MESSAGES,
      poller = @Poller(fixedRate = "500")
  )
  public Message<Map<String, String>> generate() {

    val payload = singletonMap(genKey(), genValue());

    log.info("producing {}", payload);
    return MessageBuilder.withPayload(payload).build();
  }

  public static void main(String[] args) {
    SpringApplication.run(StreamingProducerApplication.class, args);
  }
}
