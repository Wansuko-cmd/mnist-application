@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.androidLibrary)
}

android {
    namespace = "com.template.data"
}

dependencies {
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)
    testImplementation(libs.bundles.test)
}
