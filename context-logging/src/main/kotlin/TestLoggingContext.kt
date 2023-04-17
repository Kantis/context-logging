package com.github.kantis.contextlogging

import org.slf4j.event.Level

class TestLoggingContext : LoggingContext {
    val recordedLogs: MutableList<LogEvent> = mutableListOf()

    override fun info(messageFn: MessageProducer) {
        recordedLogs.add(Level.INFO(messageFn))
    }

    override fun info(messageFn: MessageProducer, throwable: Throwable) {
        recordedLogs.add(Level.INFO(throwable, messageFn))
    }

    override fun debug(messageFn: MessageProducer) {
        recordedLogs.add(Level.DEBUG(messageFn))
    }

    override fun debug(messageFn: MessageProducer, throwable: Throwable) {
        recordedLogs.add(Level.DEBUG(throwable, messageFn))
    }

    override fun trace(messageFn: MessageProducer) {
        recordedLogs.add(Level.TRACE(messageFn))
    }

    override fun trace(messageFn: MessageProducer, throwable: Throwable) {
        recordedLogs.add(Level.TRACE(throwable, messageFn))
    }

    override fun warn(messageFn: MessageProducer) {
        recordedLogs.add(Level.WARN(messageFn))
    }

    override fun warn(messageFn: MessageProducer, throwable: Throwable) {
        recordedLogs.add(Level.WARN(throwable, messageFn))
    }

    override fun error(messageFn: MessageProducer) {
        recordedLogs.add(Level.ERROR(messageFn))
    }

    override fun error(messageFn: MessageProducer, throwable: Throwable) {
        recordedLogs.add(Level.ERROR(throwable, messageFn))
    }
}

public fun withTestLogging(block: TestLoggingContext.() -> Unit): List<LogEvent> {
    val context = TestLoggingContext()
    context.block()
    return context.recordedLogs
}
