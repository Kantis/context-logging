plugins {
   id("conventions.lang.kotlin-multiplatform-js")
   id("conventions.lang.kotlin-multiplatform-jvm")
   id("conventions.lang.kotlin-multiplatform-native")
   id("conventions.publishing.maven-publish")
}

kotlin {
   sourceSets {
      commonMain {
         dependencies {
            implementation(projects.contextLoggingCore)
            implementation(libs.slf4j.api)
            implementation(libs.kotest.assertionsApi)
            implementation(libs.kotest.assertionsCore)
            implementation(libs.kotest.frameworkEngine)
         }
      }

      commonTest {
         dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotest.frameworkEngine)
            implementation(libs.kotest.property)
         }
      }
   }
}

