import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

dependencies {
    "implementation"(libs.androidx.core.ktx)
    "implementation"(libs.androidx.lifecycle.runtime.ktx)
    "implementation"(libs.androidx.activity.compose)
    "implementation"(platform(libs.androidx.compose.bom))
    "implementation"(libs.androidx.ui)
    "implementation"(libs.androidx.ui.graphics)
    "implementation"(libs.androidx.ui.tooling.preview)
    "debugImplementation"(libs.ui.tooling)
    "implementation"(libs.androidx.material3)
}