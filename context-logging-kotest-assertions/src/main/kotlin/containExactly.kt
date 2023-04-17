package com.github.kantis.contextlogging.kotest

import com.github.kantis.contextlogging.LogEvent
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.should

infix fun List<LogEvent>.shouldContainExactly(expectedLogEvents: List<ExpectedLogEvent>) =
   this should containExactly(*expectedLogEvents.toTypedArray())

fun containExactly(vararg expectedLogEvents: ExpectedLogEvent) = object : Matcher<List<LogEvent>> {
   override fun test(value: List<LogEvent>): MatcherResult {
      val sizeMatches = value.size == expectedLogEvents.size

      val mismatchingLogEvents = value.zip(expectedLogEvents)
         .mapIndexed { index, (actual, expected) ->
            val result = equal(expected).test(actual)
            if (result.passed()) null
            else LogEventMismatch(index, result.failureMessage())
         }
         .filterNotNull()

      return MatcherResult(
         sizeMatches && mismatchingLogEvents.isEmpty(),
         {
            buildString {
               appendLine("Verification of log events failed")
               appendLine()
               if (!sizeMatches) {
                  appendLine("Expected ${expectedLogEvents.size} log events, but got ${value.size} log events")
               }
               if (mismatchingLogEvents.any()) {
                  appendLine("Expected log events to match, but they failed on the following indices:")
                  mismatchingLogEvents.forEach { appendLine("   ${it.index} => ${it.message}") }
               }
            }.trim()
         },
         { "Expected log not to match expected, but it did." },
      )
   }
}

private fun equal(expected: ExpectedLogEvent) = object : Matcher<LogEvent> {
   override fun test(actual: LogEvent): MatcherResult {
      val levelMatches = actual.level == expected.level
      val messageMatches = actual.messageFn() == expected.message
      val actualThrowable = if (actual is LogEvent.WithThrowable) actual.throwable else null
      val throwableMatches = expected.throwableMatcher.test(actualThrowable)

      return MatcherResult(
         levelMatches && messageMatches && throwableMatches.passed(),
         {
            buildString {
               if (!levelMatches || !messageMatches) {
                  appendLine(
                     "expected LogEvent<(Level=${expected.level}, Message=${expected.message})>, " +
                        "but was <(Level=${actual.level}, Message=${actual.messageFn()})>",
                  )
               }

               if (!throwableMatches.passed()) {
                  appendLine("expected Throwable to match, but it did not:")
                  appendLine("   " + throwableMatches.failureMessage())
               }
            }
         },
         { "Expected log event to not equal, but they were" },
      )
   }
}
