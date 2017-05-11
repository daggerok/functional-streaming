package daggerok.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Messages {

  String CORRECT_MESSAGES = "correctMessages";

  @Input(CORRECT_MESSAGES)
  SubscribableChannel correctMessages();

  String INCORRECT_MESSAGES = "incorrectMessages";

  @Input(INCORRECT_MESSAGES)
  SubscribableChannel incorrectMessages();
}
