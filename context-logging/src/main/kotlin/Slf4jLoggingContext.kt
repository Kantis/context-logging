package com.github.kantis.contextlogging

import org.slf4j.Logger

class Slf4jLoggingContext(private val delegate: Logger) : LoggingContext {
    override fun info(messageFn: MessageProducer) {
        if (delegate.isInfoEnabled) delegate.info(messageFn())
    }

    override fun info(messageFn: MessageProducer, throwable: Throwable) {
        if (delegate.isInfoEnabled) delegate.info(messageFn(), throwable)
    }

    override fun debug(messageFn: MessageProducer) {
        if (delegate.isDebugEnabled) delegate.debug(messageFn())
    }

    override fun debug(messageFn: MessageProducer, throwable: Throwable) {
        if (delegate.isDebugEnabled) delegate.debug(messageFn(), throwable)
    }

    override fun trace(messageFn: MessageProducer) {
        if (delegate.isTraceEnabled) delegate.trace(messageFn())
    }

    override fun trace(messageFn: MessageProducer, throwable: Throwable) {
        if (delegate.isTraceEnabled) delegate.trace(messageFn(), throwable)
    }

    override fun warn(messageFn: MessageProducer) {
        if (delegate.isWarnEnabled) delegate.warn(messageFn())
    }

    override fun warn(messageFn: MessageProducer, throwable: Throwable) {
        if (delegate.isWarnEnabled) delegate.warn(messageFn(), throwable)
    }

    override fun error(messageFn: MessageProducer) {
        if (delegate.isErrorEnabled) delegate.error(messageFn())
    }

    override fun error(messageFn: MessageProducer, throwable: Throwable) {
        if (delegate.isErrorEnabled) delegate.error(messageFn(), throwable)
    }
}
