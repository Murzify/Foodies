plugins {
    id("foodies.android-lib")
    id("foodies.android-compose")
    id("foodies.hilt")
    alias(libs.plugins.serialization)
}

android.namespace = "com.murzify.foodies.feature.menu"

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    implementation(libs.kotlinx.serialization)

    implementation(libs.coil)
    implementation(libs.shimmer)

    implementation(libs.decompose)
    implementation(libs.decompose.extensions)

    debugImplementation(libs.ui.tooling)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}