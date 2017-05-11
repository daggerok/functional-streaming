package daggerok.bindings;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Messages {

  String MESSAGES = "messages";

  @Output(MESSAGES)
  MessageChannel messages();
}
