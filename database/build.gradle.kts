plugins {
    alias(libs.plugins.mistplayfeed.android.library)
    alias(libs.plugins.mistplayfeed.hilt)
    alias(libs.plugins.mistplayfeed.android.room)
}

android {
    namespace = "com.abhi.mistplayfeed.database"
}

dependencies {
    implementation(projects.model)
}
