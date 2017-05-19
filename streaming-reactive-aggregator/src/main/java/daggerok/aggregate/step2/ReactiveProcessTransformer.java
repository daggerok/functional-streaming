package daggerok.aggregate.step2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

@SpringBootApplication
@EnableBinding(Processor.class)
public class ReactiveProcessTransformer {

  @Transformer(
      inputChannel = Processor.INPUT,
      outputChannel = Processor.OUTPUT
  )
  public String transformProc(final String message) {
    return "m" + message;
  }
}
