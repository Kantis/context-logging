package io.github.kantis.contextlogging

interface LoggingContext {
   fun info(messageFn: () -> String)
   fun debug(messageFn: () -> String)
   fun trace(messageFn: () -> String)
   fun warn(messageFn: () -> String)
   fun error(messageFn: () -> String)

   fun info(throwable: Throwable, messageFn: () -> String)
   fun debug(throwable: Throwable, messageFn: () -> String)
   fun trace(throwable: Throwable, messageFn: () -> String)
   fun warn(throwable: Throwable, messageFn: () -> String)
   fun error(throwable: Throwable, messageFn: () -> String)
}
