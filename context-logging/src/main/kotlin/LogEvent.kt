package com.github.kantis.contextlogging

import org.slf4j.event.Level

sealed interface LogEvent {
    val level: Level

    data class Message(override val level: Level, val messageFn: MessageProducer) : LogEvent

    data class WithThrowable(
        override val level: Level,
        val throwable: Throwable,
        val messageFn: MessageProducer,
    ) : LogEvent
}
