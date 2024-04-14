plugins {
    id("foodies.android-lib")
    id("foodies.android-compose")
}

android.namespace = "com.murzify.foodies.core.ui"


dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}