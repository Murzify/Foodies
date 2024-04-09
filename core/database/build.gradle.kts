plugins {
    id("gosporttest.android-lib")
    id("gosporttest.hilt")
}

android.namespace = "com.murzify.gosporttest.core.database"

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}