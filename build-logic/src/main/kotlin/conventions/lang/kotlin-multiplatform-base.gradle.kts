package conventions.lang

import conventions.BuildLogicSettings
import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget
import org.jetbrains.kotlin.gradle.testing.KotlinTaskTestRun

plugins {
   id("conventions.base")
   kotlin("multiplatform")
   id("io.kotest.multiplatform")
}

// Base configuration for all Kotlin/Multiplatform conventions.
// This plugin does not enable any Kotlin target. To enable a target in a subproject, prefer
// applying specific Kotlin target convention plugins.

val settings = extensions.getByType<BuildLogicSettings>()

kotlin {
   jvmToolchain {
      languageVersion.set(JavaLanguageVersion.of(settings.jvmTarget.get()))
   }

   targets.configureEach {
      compilations.configureEach {
         kotlinOptions {
            apiVersion = settings.kotlinTarget.get()
            languageVersion = settings.kotlinTarget.get()
            freeCompilerArgs = listOf("-Xcontext-receivers")
         }
      }
   }

   // configure all Kotlin/JVM Tests to use JUnit
   targets.withType<KotlinJvmTarget>().configureEach {
      testRuns.configureEach {
         executionTask.configure {
            useJUnitPlatform()
         }
      }
   }

   sourceSets.configureEach {
      languageSettings {
         languageVersion = settings.kotlinTarget.get()
         apiVersion = settings.kotlinTarget.get()
      }
   }
}

// create lifecycle task for each Kotlin Platform, that will run all tests
KotlinPlatformType.values().forEach { kotlinPlatform ->
   val kotlinPlatformName = kotlinPlatform.name.capitalized()

   val testKotlinTargetLifecycleTask = tasks.create("allKotlin${kotlinPlatformName}Tests") {
      group = LifecycleBasePlugin.VERIFICATION_GROUP
      description = "Run all Kotlin/$kotlinPlatformName tests"
   }

   kotlin.testableTargets.matching {
      it.platformType == kotlinPlatform
   }.configureEach {
      testRuns.configureEach {
         if (this is KotlinTaskTestRun<*, *>) {
            testKotlinTargetLifecycleTask.dependsOn(executionTask)
         }
      }
   }
}
