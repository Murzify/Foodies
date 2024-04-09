plugins {
    id("gosporttest.android-lib")
}

android.namespace = "com.murzify.gosporttest.core.common"

dependencies {
    implementation(libs.decompose)
    implementation(libs.decompose.extensions)
    implementation(libs.kotlinx.coroutines.core)
}