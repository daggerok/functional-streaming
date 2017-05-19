package daggerok;

import daggerok.aggregate.step1.InputTransformer;
import daggerok.aggregate.step2.ProcessorTransformer;
import daggerok.aggregate.step3.OutputTransformer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.aggregate.AggregateApplicationBuilder;

@SpringBootApplication
public class StreamingAggregatorApplication {

  public static void main(String[] args) {
    // please make sure each processor has it's own package:
    new AggregateApplicationBuilder().from(InputTransformer.class)
                                     .via(ProcessorTransformer.class)
                                     .to(OutputTransformer.class)
                                     .run(args);
  }
}
