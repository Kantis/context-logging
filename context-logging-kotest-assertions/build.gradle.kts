plugins {
   id("conventions.lang.kotlin-multiplatform-js")
   id("conventions.lang.kotlin-multiplatform-jvm")
   id("conventions.lang.kotlin-multiplatform-native")
   id("conventions.publishing.maven-publish")
}

kotlin {
   sourceSets {
      val commonMain by getting {
         dependencies {
            implementation(projects.contextLoggingCore)
            implementation(libs.slf4j.api)
            implementation(libs.kotest.assertionsCore)
         }
      }

      val commonTest by getting {
         dependencies {
            implementation(libs.kotest.frameworkEngine)
            implementation(libs.kotest.property)
         }
      }

      if (contextLoggingSettings.enableKotlinJvm.get()) {
         val jvmTest by getting {
            dependencies {
               implementation(libs.kotest.runnerJunit5)
            }
         }
      }
   }
}

