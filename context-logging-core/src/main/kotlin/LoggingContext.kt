package com.github.kantis.contextlogging

typealias MessageProducer = () -> String

interface LoggingContext {
   fun info(messageFn: MessageProducer)
   fun debug(messageFn: MessageProducer)
   fun trace(messageFn: MessageProducer)
   fun warn(messageFn: MessageProducer)
   fun error(messageFn: MessageProducer)

   fun info(throwable: Throwable, messageFn: MessageProducer)
   fun debug(throwable: Throwable, messageFn: MessageProducer)
   fun trace(throwable: Throwable, messageFn: MessageProducer)
   fun warn(throwable: Throwable, messageFn: MessageProducer)
   fun error(throwable: Throwable, messageFn: MessageProducer)
}
