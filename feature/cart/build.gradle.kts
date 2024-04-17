plugins {
    id("foodies.android-lib")
    id("foodies.android-compose")
    id("foodies.hilt")
    id("foodies.unit-test")
    alias(libs.plugins.serialization)
}

android.namespace = "com.murzify.foodies.feature.cart"


dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))

    implementation(libs.kotlinx.serialization)

    implementation(libs.decompose)
    implementation(libs.decompose.extensions)
}