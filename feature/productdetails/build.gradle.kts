plugins {
    id("foodies.android-lib")
    id("foodies.android-compose")
    id("foodies.hilt")
    alias(libs.plugins.serialization)
}

android.namespace = "com.murzify.foodies.feature.productdetails"


dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))

    implementation(libs.kotlinx.serialization)

    implementation(libs.decompose)
    implementation(libs.decompose.extensions)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}