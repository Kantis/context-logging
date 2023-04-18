package io.github.kantis.contextlogging

public class TestLoggingContext : LoggingContext {
   val recordedLogs: MutableList<LogEvent> = mutableListOf()

   override fun info(messageFn: () -> String) {
      recordedLogs.add(LogLevel.INFO(messageFn))
   }

   override fun info(throwable: Throwable, messageFn: () -> String) {
      recordedLogs.add(LogLevel.INFO(throwable, messageFn))
   }

   override fun debug(messageFn: () -> String) {
      recordedLogs.add(LogLevel.DEBUG(messageFn))
   }

   override fun debug(throwable: Throwable, messageFn: () -> String) {
      recordedLogs.add(LogLevel.DEBUG(throwable, messageFn))
   }

   override fun trace(messageFn: () -> String) {
      recordedLogs.add(LogLevel.TRACE(messageFn))
   }

   override fun trace(throwable: Throwable, messageFn: () -> String) {
      recordedLogs.add(LogLevel.TRACE(throwable, messageFn))
   }

   override fun warn(messageFn: () -> String) {
      recordedLogs.add(LogLevel.WARN(messageFn))
   }

   override fun warn(throwable: Throwable, messageFn: () -> String) {
      recordedLogs.add(LogLevel.WARN(throwable, messageFn))
   }

   override fun error(messageFn: () -> String) {
      recordedLogs.add(LogLevel.ERROR(messageFn))
   }

   override fun error(throwable: Throwable, messageFn: () -> String) {
      recordedLogs.add(LogLevel.ERROR(throwable, messageFn))
   }
}

public inline fun withTestLogging(block: TestLoggingContext.() -> Unit): List<LogEvent> {
   val context = TestLoggingContext()
   context.block()
   return context.recordedLogs
}
