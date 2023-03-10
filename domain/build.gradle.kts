@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.androidLibrary)
}

android {
    namespace = "com.template.domain"
}

dependencies {
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)
    testImplementation(libs.bundles.test)
}
