plugins {
    alias(libs.plugins.mistplayfeed.android.library)
    alias(libs.plugins.mistplayfeed.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.abhi.mistplayfeed.data"
}

dependencies {
    implementation(projects.model)
    implementation(projects.database)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
}