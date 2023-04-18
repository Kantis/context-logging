import io.github.kantis.contextlogging.LoggingContext
import org.slf4j.Logger

public class Slf4jLoggingContext(private val delegate: Logger) : LoggingContext {
   override fun info(messageFn: () -> String) {
      if (delegate.isInfoEnabled) delegate.info(messageFn())
   }

   override fun info(throwable: Throwable, messageFn: () -> String) {
      if (delegate.isInfoEnabled) delegate.info(messageFn(), throwable)
   }

   override fun debug(messageFn: () -> String) {
      if (delegate.isDebugEnabled) delegate.debug(messageFn())
   }

   override fun debug(throwable: Throwable, messageFn: () -> String) {
      if (delegate.isDebugEnabled) delegate.debug(messageFn(), throwable)
   }

   override fun trace(messageFn: () -> String) {
      if (delegate.isTraceEnabled) delegate.trace(messageFn())
   }

   override fun trace(throwable: Throwable, messageFn: () -> String) {
      if (delegate.isTraceEnabled) delegate.trace(messageFn(), throwable)
   }

   override fun warn(messageFn: () -> String) {
      if (delegate.isWarnEnabled) delegate.warn(messageFn())
   }

   override fun warn(throwable: Throwable, messageFn: () -> String) {
      if (delegate.isWarnEnabled) delegate.warn(messageFn(), throwable)
   }

   override fun error(messageFn: () -> String) {
      if (delegate.isErrorEnabled) delegate.error(messageFn())
   }

   override fun error(throwable: Throwable, messageFn: () -> String) {
      if (delegate.isErrorEnabled) delegate.error(messageFn(), throwable)
   }
}
