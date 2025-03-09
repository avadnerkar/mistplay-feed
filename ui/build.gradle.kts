plugins {
    alias(libs.plugins.mistplayfeed.android.library)
    alias(libs.plugins.mistplayfeed.android.library.compose)
}

android {
    namespace = "com.abhi.mistplayfeed.ui"
}

dependencies {
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
}
