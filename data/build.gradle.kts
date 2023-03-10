@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.androidLibrary)
}

android {
    namespace = "com.template.data"
    buildFeatures {
        mlModelBinding = true
    }
}

dependencies {
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.tensorflow.lite)

    testImplementation(libs.bundles.test)
}
