plugins {
    alias(libs.plugins.mistplayfeed.android.application)
    alias(libs.plugins.mistplayfeed.android.application.compose)
    alias(libs.plugins.mistplayfeed.hilt)
}

android {
    namespace = "com.abhi.mistplayfeed"

    defaultConfig {
        applicationId = "com.abhi.mistplayfeed"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            //Just use debug signing - for a production build we'd need to generate a signing key
            signingConfig = signingConfigs.named("debug").get()
            //For a real application we'd enable minification - leaving it disabled for now due to time limitations in testing release build
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.feature.userList)
    implementation(projects.feature.userDetail)
    implementation(projects.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
}
