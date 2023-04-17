rootProject.name = "context-logging"

plugins {
   id("com.gradle.enterprise") version "3.13"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

apply(from = "build-logic/repositories.gradle.kts")

includeBuild("build-logic")
include(
   "context-logging-core",
   "context-logging-kotest-assertions",
   "context-logging-slf4j",
)

gradleEnterprise {

   buildScan {
      val isCI = providers.environmentVariable("CI").orNull.toBoolean()

      tag(if (isCI) "CI" else "local")
      tag(providers.systemProperty("os.name").orNull)
      tag(providers.systemProperty("os.arch").orNull)

      if (isCI) {
         // only automatically enable build scan on CI
         termsOfServiceUrl = "https://gradle.com/terms-of-service"
         termsOfServiceAgree = "yes"
         publishAlways()
         isUploadInBackground = false

         val ghServer = providers.environmentVariable("GITHUB_SERVER_URL").orNull
         val ghRepo = providers.environmentVariable("GITHUB_REPOSITORY").orNull
         val giRunId = providers.environmentVariable("GITHUB_RUN_ID").orNull
         link("GitHub Workflow run", "$ghServer/$ghRepo/actions/runs/$giRunId")
      }
   }
}
