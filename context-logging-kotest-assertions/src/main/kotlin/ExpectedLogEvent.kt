package com.github.kantis.contextlogging.kotest

import io.kotest.matchers.Matcher
import io.kotest.matchers.be
import io.kotest.matchers.nulls.beNull
import org.slf4j.event.Level

data class ExpectedLogEvent(
   val level: Level,
   val message: String,
   val throwableMatcher: Matcher<Throwable?> = beNull(),
)

operator fun Level.invoke(message: String) = ExpectedLogEvent(this, message)
operator fun Level.invoke(throwable: Throwable, message: String) = ExpectedLogEvent(this, message, be(throwable))
operator fun Level.invoke(throwableMatcher: Matcher<Throwable?>, message: String) = ExpectedLogEvent(this, message, throwableMatcher)
