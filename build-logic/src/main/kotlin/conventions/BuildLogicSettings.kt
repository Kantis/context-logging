package conventions

import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import javax.inject.Inject

/**
 * Common settings for configuring Context Logging's build logic.
 *
 * The settings need to be accessible during configuration, so they should come from Gradle
 * properties or environment variables.
 */
abstract class BuildLogicSettings @Inject constructor(
   private val providers: ProviderFactory,
) {

   val kotlinTarget: Provider<String> = setting("kotlinTarget", "1.6")
   val jvmTarget: Provider<String> = setting("jvmTarget", "11")

   /** Controls whether Kotlin Multiplatform JVM is enabled */
   val enableKotlinJvm: Provider<Boolean> = flag("enableKotlinJvm", true)
   /** Controls whether Kotlin Multiplatform JS is enabled */
   val enableKotlinJs: Provider<Boolean> = flag("enableKotlinJs", true)
   /** Controls whether Kotlin Multiplatform Native is enabled */
   val enableKotlinNative: Provider<Boolean> = flag("enableKotlinNative", false)

   /**
    * Comma separated list of MavenPublication names that will have the publishing task enabled.
    * The provided names will be matched ignoring case, and by prefix, so `iOS` will match
    * `iosArm64`, `iosX64`, and `iosSimulatorArm64`.
    *
    * This is used to avoid duplicate publications, which can occur when a Kotlin Multiplatform
    * project is published in CI/CD on different host machines (Linux, Windows, and macOS).
    *
    * For example, by including `jvm` in the values when publishing on Linux, but omitting `jvm` on
    * Windows and macOS, this results in any Kotlin/JVM publications only being published once.
    */
   val enabledPublicationNamePrefixes: Provider<Set<String>> =
      setting("enabledPublicationNamePrefixes", "KotlinMultiplatform,Jvm,Js,iOS,macOS,watchOS,tvOS,mingw")
         .map { enabledPlatforms ->
            enabledPlatforms
               .split(",")
               .map { it.trim() }
               .filter { it.isNotBlank() }
               .toSet()
         }

   private fun setting(name: String, default: String? = null) =
      providers.gradleProperty("contextLogging_$name")
         .orElse(providers.provider { default }) // workaround for https://github.com/gradle/gradle/issues/12388

   private fun flag(name: String, default: Boolean) =
      providers.gradleProperty("contextLogging_$name").map { it.toBoolean() }.orElse(default)

   companion object {
      const val EXTENSION_NAME = "contextLoggingSettings"

      /**
       * Regex for matching the release version.
       *
       * If a version does not match this code it should be treated as a SNAPSHOT version.
       */
      val releaseVersionRegex = Regex("\\d+.\\d+.\\d+")
   }
}
