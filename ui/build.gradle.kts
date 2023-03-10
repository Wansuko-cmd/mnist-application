plugins {
    id(Plugins.androidCompose)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.template.ui"
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.androidx.camera)
    implementation(libs.accompanist.permissions)

    testImplementation(libs.bundles.test)

    androidTestImplementation(libs.bundles.androidx.test)
}
