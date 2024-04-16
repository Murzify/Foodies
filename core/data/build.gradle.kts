plugins {
    id("foodies.android-lib")
    id("foodies.hilt")
    id("foodies.unit-test")
}

android.namespace = "com.murzify.foodies.core.data"

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
}