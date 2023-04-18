package conventions.lang

import conventions.BuildLogicSettings

plugins {
   id("conventions.lang.kotlin-multiplatform-base")
}

val settings = extensions.getByType<BuildLogicSettings>()

if (settings.enableKotlinJs.get()) {
   kotlin {
      targets {
         js(IR) {
            browser()
            nodejs()
         }
      }

      sourceSets {
         val commonMain by getting {}
         val commonTest by getting {}

         val jsMain by getting {
            dependsOn(commonMain)
         }

         val jsTest by getting {
            dependsOn(commonTest)
         }
      }
   }

   relocateKotlinJsStore()
}
