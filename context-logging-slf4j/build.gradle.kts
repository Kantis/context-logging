plugins {
   id("conventions.lang.kotlin-multiplatform-jvm")
   id("conventions.publishing.maven-publish")
}

kotlin {
   sourceSets {
      if (contextLoggingSettings.enableKotlinJvm.get()) {
         val jvmMain by getting {
            dependencies {
               api(projects.contextLoggingCore)
               implementation(libs.slf4j.api)
            }
         }
      }
   }
}
