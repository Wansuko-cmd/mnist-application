plugins {
    id(Plugins.androidCompose)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.template.ui"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase"))
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.androidx.camera)

    testImplementation(libs.bundles.test)

    androidTestImplementation(libs.bundles.androidx.test)
}
