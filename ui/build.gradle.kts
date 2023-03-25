plugins {
    id(Plugins.androidCompose)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.template.ui"
}

dependencies {
    implementation(project(":data"))
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)

    testImplementation(libs.bundles.test)

    androidTestImplementation(libs.bundles.androidx.test)
}
