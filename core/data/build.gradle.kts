plugins {
    id("foodies.android-lib")
    id("foodies.hilt")
}

android.namespace = "com.murzify.foodies.core.data"

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))

    testImplementation(libs.junit)
}