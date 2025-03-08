plugins {
    alias(libs.plugins.mistplayfeed.jvm.library)
    alias(libs.plugins.mistplayfeed.hilt)
}

dependencies {
    implementation(projects.core)
    implementation(libs.kotlinx.coroutines.core)
}
