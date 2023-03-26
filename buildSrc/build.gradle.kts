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
            id = "com.wsr.mnist.application"
            implementationClass = "plugins.AndroidApplicationPlugin"
        }
        register("androidCompose") {
            id = "com.wsr.mnist.compose"
            implementationClass = "plugins.AndroidComposePlugin"
        }
        register("androidLibrary") {
            id = "com.wsr.mnist.library"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }
        register("daggerHilt") {
            id = "com.wsr.mnist.dagger-hilt"
            implementationClass = "plugins.DaggerHiltPlugin"
        }
    }
}
