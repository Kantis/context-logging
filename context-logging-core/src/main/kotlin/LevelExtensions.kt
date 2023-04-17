package com.github.kantis.contextlogging

import org.slf4j.event.Level

// Provide extension on Slf4J log levels: Level.INFO { "message"  }
// Note: These extensions only construct data, they do not actually log anything.
internal operator fun Level.invoke(messageFn: () -> String) = LogEvent.Message(this, messageFn)
internal operator fun Level.invoke(throwable: Throwable, messageFn: () -> String) = LogEvent.WithThrowable(this, throwable, messageFn)
