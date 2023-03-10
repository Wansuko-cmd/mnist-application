@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.androidApplication)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.template"

    defaultConfig {
        applicationId = "com.template"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles.add(getDefaultProguardFile("proguard-android-optimize.txt"))
            proguardFiles.add(file("proguard-rules.pro"))
        }
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":ui"))

    implementation(libs.bundles.androidx)
    testImplementation(libs.bundles.test)
}
