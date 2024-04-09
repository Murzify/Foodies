import com.android.build.gradle.BaseExtension

plugins {
    id("com.android.application")
    id("gosporttest.android")
}

configure<BaseExtension> {
    namespace = "com.murzify.gosporttest"
    defaultConfig {
        applicationId = "com.murzify.gosporttest"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
}