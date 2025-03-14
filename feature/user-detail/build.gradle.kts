plugins {
    alias(libs.plugins.mistplayfeed.android.feature)
    alias(libs.plugins.mistplayfeed.android.library.compose)
}

android {
    namespace = "com.abhi.mistplayfeed.userDetail"
}

dependencies {
    implementation(projects.data)
    implementation(projects.model)
    implementation(projects.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    testImplementation(projects.testing)
}