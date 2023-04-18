
## Usage

Enable context receivers for your project and add the dependency for the logging framework you're using
(TODO: currently only slf4j has been added, but planning to add for kotlin-logging and logback as well)

### Gradle
```kotlin
dependencies {
    implementation("io.github.kantis:context-logging-slf4j:0.0.1-SNAPSHOT")
}
```

### Maven
```xml

<dependency>
  <groupId>io.github.kantis</groupId>
  <artifactId>context-logging-slf4j-jvm</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Writing logs

Any code which wants to write logs can depend on the `LoggingContext` which exposes methods for logging.

```kotlin
context(LoggingContext)
fun logHello() {
    info { "Hello" }
}
```

### Testing logging
There's a function called `withTestLogging` which provides a recording logging context. It results in a `List<LogEvent>`, for which
there's convenient assertions added in the `context-logging-kotest-assertions` module.

```kotlin
test("verify logging") {
    withTestLogging {
        logHello()
        logHello()
    }.shouldContainExactly(
        LogLevel.INFO("Hello"), // the operator function LogLevel.invoke creates an `ExpectedLogEvent` which the custom assertion uses.
        LogLevel.INFO("Hello"),
    )
}
```
