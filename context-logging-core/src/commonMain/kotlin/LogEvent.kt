package io.github.kantis.contextlogging

public sealed interface LogEvent {
   val level: LogLevel
   val messageFn: () -> String

   data class Message(
      override val level: LogLevel,
      override val messageFn: () -> String,
   ) : LogEvent

   data class WithThrowable(
      override val level: LogLevel,
      val throwable: Throwable,
      override val messageFn: () -> String,
   ) : LogEvent
}
