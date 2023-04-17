@file:Suppress("UnstableApiUsage")

rootProject.name = "build-logic"
apply(from = "repositories.gradle.kts")

dependencyResolutionManagement {
   versionCatalogs {
      create("libs") {
         from(files("../gradle/libs.versions.toml"))
      }
   }
}
