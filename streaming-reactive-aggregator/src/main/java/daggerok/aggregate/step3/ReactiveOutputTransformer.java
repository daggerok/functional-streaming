package daggerok.aggregate.step3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

@SpringBootApplication
@EnableBinding(Processor.class)
public class ReactiveOutputTransformer {

  @Transformer(
      inputChannel = Processor.INPUT,
      outputChannel = Processor.OUTPUT
  )
  public String transformOut(final String message) {
    return "o" + message;
  }
}
