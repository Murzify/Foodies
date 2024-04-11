import com.android.build.gradle.BaseExtension

plugins {
    id("com.android.application")
    id("foodies.android")
}

configure<BaseExtension> {
    namespace = "com.murzify.foodies"
    defaultConfig {
        applicationId = "com.murzify.foodies"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
}