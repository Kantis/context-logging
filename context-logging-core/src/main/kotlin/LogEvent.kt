package com.github.kantis.contextlogging

import org.slf4j.event.Level

public sealed interface LogEvent {
   val level: Level
   val messageFn: () -> String

   data class Message(
      override val level: Level,
      override val messageFn: () -> String,
   ) : LogEvent

   data class WithThrowable(
      override val level: Level,
      val throwable: Throwable,
      override val messageFn: () -> String,
   ) : LogEvent
}
