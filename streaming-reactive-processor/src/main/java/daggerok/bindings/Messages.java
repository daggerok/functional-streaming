package daggerok.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Messages {

  String CORRECT_MESSAGES = "correctMessages";

  @Input(CORRECT_MESSAGES)
  MessageChannel correctMessages();

  String INCORRECT_MESSAGES = "incorrectMessages";

  @Input(INCORRECT_MESSAGES)
  MessageChannel incorrectMessages();

  String REACTIVE_MESSAGE = "reactiveMessages";

  @Output(REACTIVE_MESSAGE)
  MessageChannel reactiveMessages();
}
