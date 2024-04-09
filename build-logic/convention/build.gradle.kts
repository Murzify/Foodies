plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.kotlin.ksp.gradle)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("hilt") {
            id = "gosporttest.hilt"
            implementationClass = "com.murzify.gosporttest.buildlogic.convention.HIltPlugin"
        }
    }
}

