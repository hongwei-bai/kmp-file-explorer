import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlin.native.cocoapods)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            // Required when using NativeSQLiteDriver
            linkerOpts.add("-lsqlite3")
        }
    }

    cocoapods {
        version = "1.0.0"
        summary = "Shared Kotlin Multiplatform module"
        homepage = "https://example.com"
        ios.deploymentTarget = "14.1"

        framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }

        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            implementation(libs.androidx.lifecycle.common.java8)
            implementation(libs.androidx.lifecycle.process)
            implementation(libs.room.ktx)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.koin.core)
            // Koin for Compose Multiplatform - It doesn't support iOS
//            implementation("io.insert-koin:koin-compose:1.0.1")
            implementation(libs.coroutines.core)

            implementation(libs.room.runtime)
//            implementation(libs.room.ktx)
//            implementation(libs.room.common)
            implementation(libs.sqlite.bundled)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.koin.core)
        }
        iosMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.melonapp.fileexplorer"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.melonapp.fileexplorer"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        ksp(libs.room.compiler)
    }
}

dependencies {
    implementation(libs.androidx.foundation.layout.android)
    debugImplementation(compose.uiTooling)
}

dependencies {
    // KSP support for Room Compiler.
    add("kspAndroid", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}

compose.desktop {
    application {
        mainClass = "com.melonapp.fileexplorer.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.melonapp.fileexplorer"
            packageVersion = "1.0.0"
        }
    }
    dependencies {
        ksp(libs.room.compiler)
    }
}

if (!tasks.names.contains("syncFramework")) {
    tasks.register("syncFramework") {
        val platform = project.findProperty("kotlin.native.cocoapods.platform")?.toString()?.capitalize() ?: "Ios"
        val archs = project.findProperty("kotlin.native.cocoapods.archs")?.toString()?.split(" ") ?: listOf("Arm64")
        val configRaw = project.findProperty("kotlin.native.cocoapods.configuration")?.toString() ?: "Debug"
        val config = configRaw.lowercase().replaceFirstChar { it.uppercase() }  // Normalize to 'Debug' or 'Release'

        dependsOn(
            *archs.map { arch ->
                val taskName = "link${config}Framework${platform}${arch.capitalize()}"
                println("syncFramework depends on $taskName")
                tasks.named(taskName)
            }.toTypedArray()
        )
    }
}
