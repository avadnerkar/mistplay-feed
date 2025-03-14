plugins {
    alias(libs.plugins.mistplayfeed.android.library)
    alias(libs.plugins.mistplayfeed.android.room)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.abhi.mistplayfeed.model"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
