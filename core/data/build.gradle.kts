plugins {
    id("gosporttest.android-lib")
    id("gosporttest.hilt")
}

android.namespace = "com.murzify.gosporttest.core.data"

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))

    testImplementation(libs.junit)
}