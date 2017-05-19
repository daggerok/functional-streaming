package daggerok.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Messages {

  String MESSAGES = "messages";

  @Input(MESSAGES)
  SubscribableChannel messages();

  String CORRECT_MESSAGES = "correctMessages";

  @Output(CORRECT_MESSAGES)
  MessageChannel correctMessages();

  String INCORRECT_MESSAGES = "incorrectMessages";

  @Output(INCORRECT_MESSAGES)
  MessageChannel incorrectMessages();
}
