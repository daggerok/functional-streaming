package daggerok.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Messages {

  String CORRECT_MESSAGES = "correctMessages";

  @Input(CORRECT_MESSAGES)
  SubscribableChannel correctMessages();

  String INCORRECT_MESSAGES = "incorrectMessages";

  @Input(INCORRECT_MESSAGES)
  SubscribableChannel incorrectMessages();

  String REACTIVE_MESSAGE = "reactiveMessages";

  @Output(REACTIVE_MESSAGE)
  MessageChannel reactiveMessages();

  /** reactive producer */

  String REACTIVE_PROCESS = "reactiveProcess";

  @Input(REACTIVE_PROCESS)
  SubscribableChannel reactiveProcess();

  String REACTIVE_END = "reactiveEnd";

  @Output(REACTIVE_END)
  MessageChannel reactiveEnd();
}
