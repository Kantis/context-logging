package com.github.kantis.contextlogging.slf4j

import com.github.kantis.contextlogging.LoggingContext
import com.github.kantis.contextlogging.MessageProducer
import org.slf4j.Logger

public class Slf4jLoggingContext(private val delegate: Logger) : LoggingContext {
   override fun info(messageFn: MessageProducer) {
      if (delegate.isInfoEnabled) delegate.info(messageFn())
   }

   override fun info(throwable: Throwable, messageFn: MessageProducer) {
      if (delegate.isInfoEnabled) delegate.info(messageFn(), throwable)
   }

   override fun debug(messageFn: MessageProducer) {
      if (delegate.isDebugEnabled) delegate.debug(messageFn())
   }

   override fun debug(throwable: Throwable, messageFn: MessageProducer) {
      if (delegate.isDebugEnabled) delegate.debug(messageFn(), throwable)
   }

   override fun trace(messageFn: MessageProducer) {
      if (delegate.isTraceEnabled) delegate.trace(messageFn())
   }

   override fun trace(throwable: Throwable, messageFn: MessageProducer) {
      if (delegate.isTraceEnabled) delegate.trace(messageFn(), throwable)
   }

   override fun warn(messageFn: MessageProducer) {
      if (delegate.isWarnEnabled) delegate.warn(messageFn())
   }

   override fun warn(throwable: Throwable, messageFn: MessageProducer) {
      if (delegate.isWarnEnabled) delegate.warn(messageFn(), throwable)
   }

   override fun error(messageFn: MessageProducer) {
      if (delegate.isErrorEnabled) delegate.error(messageFn())
   }

   override fun error(throwable: Throwable, messageFn: MessageProducer) {
      if (delegate.isErrorEnabled) delegate.error(messageFn(), throwable)
   }
}
