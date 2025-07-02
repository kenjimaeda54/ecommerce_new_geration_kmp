import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)

    //serialization
    alias(libs.plugins.serialization)

    //touchlab para view model
    alias(libs.plugins.touchlab.skie)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.coroutines.ktx)
            implementation(libs.touchlab.stately.common)
            implementation(libs.koin.core)
            implementation(libs.ktor.negotiation)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.client.core)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.touchlab.stately.isolate)
            implementation(libs.touchlab.stately.common)
        }

        androidMain.dependencies {
            implementation(libs.viewModel.ktx)
            implementation(libs.koin.android)
            implementation(libs.ktor.client.android)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.ecommercenewgeration"
    compileSdk = 35
    defaultConfig {
        minSdk  = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
