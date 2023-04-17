package com.github.kantis.contextlogging

typealias MessageProducer = () -> String

interface LoggingContext {
    fun info(messageFn: MessageProducer)
    fun debug(messageFn: MessageProducer)
    fun trace(messageFn: MessageProducer)
    fun warn(messageFn: MessageProducer)
    fun error(messageFn: MessageProducer)

    fun info(messageFn: MessageProducer, throwable: Throwable)
    fun debug(messageFn: MessageProducer, throwable: Throwable)
    fun trace(messageFn: MessageProducer, throwable: Throwable)
    fun warn(messageFn: MessageProducer, throwable: Throwable)
    fun error(messageFn: MessageProducer, throwable: Throwable)
}
