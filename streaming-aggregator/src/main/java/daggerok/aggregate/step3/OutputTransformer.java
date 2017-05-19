package daggerok.aggregate.step3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

import java.util.Map;

import static java.util.Collections.singletonMap;

@SpringBootApplication
@EnableBinding(Processor.class)
public class OutputTransformer {

  @Transformer(
      inputChannel = Processor.INPUT,
      outputChannel = Processor.OUTPUT
  )
  public Map<String, String> transformOut(final String message) {
    return singletonMap("res", "out->" + message);
  }
}
