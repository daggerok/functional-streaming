package daggerok.aggregate.step1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

@Slf4j
@SpringBootApplication
@EnableBinding(Processor.class)
public class InputTransformer {

  @Transformer(
      inputChannel = Processor.INPUT,
      outputChannel = Processor.OUTPUT
  )
  public String transformIn(final Message<String> message) {

    return "in->" + message.getPayload()
                           .replaceAll("\"", "'");
  }
}
