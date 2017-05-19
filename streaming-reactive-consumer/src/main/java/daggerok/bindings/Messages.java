package daggerok.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface Messages {

  String REACTIVE_MESSAGE = "reactiveMessages";

  @Input(REACTIVE_MESSAGE)
  MessageChannel reactiveMessages();

  String REACTIVE_END = "reactiveEnd";

  @Input(REACTIVE_END)
  MessageChannel reactiveEnd();
}
