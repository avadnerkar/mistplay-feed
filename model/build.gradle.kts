plugins {
    alias(libs.plugins.mistplayfeed.android.library)
    alias(libs.plugins.mistplayfeed.android.room)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
