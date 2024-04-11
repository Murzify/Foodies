plugins {
    id("foodies.android-lib")
    id("foodies.hilt")
}

android.namespace = "com.murzify.foodies.core.database"

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}