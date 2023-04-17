plugins {
   id("conventions.lang.kotlin-multiplatform-js")
   id("conventions.lang.kotlin-multiplatform-jvm")
   id("conventions.lang.kotlin-multiplatform-native")
   id("conventions.publishing.maven-publish")
}

dependencies {
   implementation(libs.slf4j.api)
   testImplementation(libs.kotest.runnerJunit5)
   testImplementation(libs.kotest.property)
}
