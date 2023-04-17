package conventions.lang

import conventions.BuildLogicSettings

plugins {
   id("conventions.lang.kotlin-multiplatform-base")
}

val settings = extensions.getByType<BuildLogicSettings>()

if (settings.enableKotlinJvm.get()) {
   kotlin {
      jvm {
         withJava()
      }
   }
}
