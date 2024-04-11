plugins {
    id("foodies.android-lib")
}

android.namespace = "com.murzify.foodies.core.common"

dependencies {
    implementation(libs.decompose)
    implementation(libs.decompose.extensions)
    implementation(libs.kotlinx.coroutines.core)
}