plugins {
    id(Plugins.androidCompose)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.template.ui"
}

dependencies {
    implementation(libs.bundles.androidx)

    testImplementation(libs.bundles.test)

    androidTestImplementation(libs.bundles.androidx.test)
}
