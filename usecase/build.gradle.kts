@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.template.usecase"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)
    testImplementation(libs.bundles.test)
}
