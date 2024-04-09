plugins {
    id("gosporttest.android-lib")
    id("gosporttest.android-compose")
    id("gosporttest.hilt")
}

android.namespace = "com.murzify.gosporttest.feature.menu"

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    implementation(libs.coil)
    implementation(libs.shimmer)

    implementation(libs.decompose)
    implementation(libs.decompose.extensions)

    debugImplementation(libs.ui.tooling)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}