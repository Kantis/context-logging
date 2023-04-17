import com.github.kantis.contextlogging.LoggingContext
import com.github.kantis.contextlogging.kotest.invoke
import com.github.kantis.contextlogging.kotest.shouldContainExactly
import com.github.kantis.contextlogging.withTestLogging
import io.kotest.assertions.shouldFail
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import org.slf4j.event.Level.ERROR
import org.slf4j.event.Level.INFO

class ContainExactlyTest : FreeSpec(
   {
      "Empty log matches" {
         withTestLogging {
            notLogging()
         } shouldContainExactly emptyList()
      }

      "Single log matches" {
         withTestLogging {
            greet()
         } shouldContainExactly listOf(
            INFO("hello"),
         )
      }

      "Multiple logs matches" {
         withTestLogging {
            greet()
            panic()
         } shouldContainExactly listOf(
            INFO("hello"),
            ERROR("panic"),
         )
      }

      "When expectation fails" - {
         "Missing throwable" {
            shouldFail {
               withTestLogging {
                  logException()
               } shouldContainExactly listOf(
                  ERROR("panic"),
               )
            }.message shouldBe """
            Verification of log events failed

            Expected log events to match, but they failed on the following indices:
               0 => expected Throwable to match, but it did not:
               Expected value to be null, but was java.lang.RuntimeException: mayday.
            """.trimIndent()
         }

         "Mismatching level and message" {
            shouldFail {
               withTestLogging {
                  greet()
               } shouldContainExactly listOf(
                  ERROR("panic"),
               )
            }.message shouldBe """
            Verification of log events failed

            Expected log events to match, but they failed on the following indices:
               0 => expected LogEvent<(Level=ERROR, Message=panic)>, but was <(Level=INFO, Message=hello)>
            """.trimIndent()
         }

         "Mismatching number of entries and events" {
            shouldFail {
               withTestLogging {
                  greet()
                  greet()
                  panic()
               } shouldContainExactly listOf(
                  INFO("hello"),
                  ERROR("panic"),
               )
            }.message shouldBe """
            Verification of log events failed

            Expected 2 log events, but got 3 log events
            Expected log events to match, but they failed on the following indices:
               1 => expected LogEvent<(Level=ERROR, Message=panic)>, but was <(Level=INFO, Message=hello)>
            """.trimIndent()
         }
      }
   },
)

context(LoggingContext)
fun notLogging() {
}

context(LoggingContext)
fun greet() {
   info { "hello" }
}

context(LoggingContext)
fun panic() {
   error { "panic" }
}

context(LoggingContext)
fun logException() {
   error(RuntimeException("mayday")) { "panic" }
}
