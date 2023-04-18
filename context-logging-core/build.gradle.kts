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
            implementation(libs.kotest.assertionsCore)
         }
      }

      val commonTest by getting {
         dependencies {
            implementation(libs.kotest.frameworkEngine)
            implementation(libs.kotest.property)
         }
      }
   }
}
