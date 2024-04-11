plugins {
    id("foodies.android-app")
    id("foodies.android-compose")
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
}

dependencies {

    implementation(project(":feature:catalog"))
    implementation(libs.kotlinx.serialization)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    
    implementation(libs.decompose)
    implementation(libs.decompose.extensions)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
android {
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}
