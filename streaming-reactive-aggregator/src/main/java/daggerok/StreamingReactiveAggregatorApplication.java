package daggerok;

import daggerok.aggregate.step1.ReactiveInputTransformer;
import daggerok.aggregate.step2.ReactiveProcessTransformer;
import daggerok.aggregate.step3.ReactiveOutputTransformer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.aggregate.AggregateApplicationBuilder;

@SpringBootApplication
public class StreamingReactiveAggregatorApplication {

  public static void main(String[] args) {
    // please make sure each processor has it's own package:
    new AggregateApplicationBuilder().from(ReactiveInputTransformer.class)
                                     .via(ReactiveProcessTransformer.class)
                                     .to(ReactiveOutputTransformer.class)
                                     .run(args);
  }
}
