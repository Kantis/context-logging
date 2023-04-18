package io.github.kantis.contextlogging.kotest

import io.github.kantis.contextlogging.LogLevel
import io.kotest.matchers.Matcher
import io.kotest.matchers.be
import io.kotest.matchers.nulls.beNull

data class ExpectedLogEvent(
   val level: LogLevel,
   val message: String,
   val throwableMatcher: Matcher<Throwable?> = beNull(),
)

operator fun LogLevel.invoke(message: String) = ExpectedLogEvent(this, message)
operator fun LogLevel.invoke(throwable: Throwable, message: String) = ExpectedLogEvent(this, message, be(throwable))
operator fun LogLevel.invoke(throwableMatcher: Matcher<Throwable?>, message: String) = ExpectedLogEvent(this, message, throwableMatcher)
