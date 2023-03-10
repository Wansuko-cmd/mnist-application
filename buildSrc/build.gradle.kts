plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.android)
    implementation(libs.gradle.hilt)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.template.application"
            implementationClass = "plugins.AndroidApplicationPlugin"
        }
        register("androidCompose") {
            id = "com.template.compose"
            implementationClass = "plugins.AndroidComposePlugin"
        }
        register("androidLibrary") {
            id = "com.template.library"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }
        register("daggerHilt") {
            id = "com.template.dagger-hilt"
            implementationClass = "plugins.DaggerHiltPlugin"
        }
    }
}
