package com.github.kantis.contextlogging

// Provide extension on Slf4J log levels: Level.INFO { "message"  }
// Note: These extensions only construct data, they do not actually log anything.
internal operator fun LogLevel.invoke(messageFn: () -> String) = LogEvent.Message(this, messageFn)
internal operator fun LogLevel.invoke(throwable: Throwable, messageFn: () -> String) = LogEvent.WithThrowable(this, throwable, messageFn)
