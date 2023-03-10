@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.template.data"
    buildFeatures {
        mlModelBinding = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase"))
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.tensorflow.lite)

    testImplementation(libs.bundles.test)
}
