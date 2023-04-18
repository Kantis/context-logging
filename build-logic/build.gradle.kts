plugins {
   `kotlin-dsl`
}

repositories {
   gradlePluginPortal()
   mavenCentral()
}

dependencies {
   implementation(libs.gradlePlugin.kotlin)
   implementation(libs.gradlePlugin.ktlint)
   implementation(libs.gradlePlugin.testlogger)
   implementation(libs.gradlePlugin.detekt)
   implementation(libs.gradlePlugin.kotest)
}
