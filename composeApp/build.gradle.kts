import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("app.cash.sqldelight") version "2.0.1"
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
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.sqldelight.android.driver)
            implementation(libs.koin.android)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            implementation(libs.androidx.lifecycle.common.java8)
            implementation(libs.androidx.lifecycle.process)
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
            implementation(libs.sqldelight.runtime)
            implementation(libs.koin.core)
            // Koin for Compose Multiplatform - It doesn't support iOS
//            implementation("io.insert-koin:koin-compose:1.0.1")
            implementation(libs.coroutines.core)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.sqldelight.sqlite.driver)
            implementation(libs.koin.core)
        }
        iosMain.dependencies {
            implementation(libs.sqldelight.native.driver)
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.example.kmpdemo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.kmpdemo"
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
}

dependencies {
    implementation(libs.androidx.foundation.layout.android)
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.example.kmpdemo.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.example.kmpdemo"
            packageVersion = "1.0.0"
        }
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example.kmpdemo.database")
//            dialect(libs.sqlite.dialect)

            // Optional: Add this if you want to generate a schema file
            generateAsync.set(false)
        }
    }

    // Optional: Link SQLDelight with Kotlin/Native memory model
    linkSqlite.set(true)
}
