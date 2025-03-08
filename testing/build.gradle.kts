plugins {
    alias(libs.plugins.mistplayfeed.android.library)
    alias(libs.plugins.mistplayfeed.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.abhi.mistplayfeed.testing"
}

dependencies {
    implementation(projects.model)
    implementation(projects.data)
    api(libs.kotlinx.coroutines.test)
    implementation(libs.androidx.test.rules)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt.android.testing)
}
