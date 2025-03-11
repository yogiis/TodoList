plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)


//    id("kotlin-kapt") // ✅ Pastikan ini ada
}

android {
    namespace = "com.zazuba.todolist"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.zazuba.todolist"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
//    implementation("com.google.dagger:hilt-android:2.51.1")
//    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
//
//    // Hilt untuk pengujian
//    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
//    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.51.1")







    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.hilt.android.testing)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation("androidx.navigation:navigation-compose:2.8.8")
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // UI Testing untuk Jetpack Compose
    androidTestImplementation("androidx.compose.ui:ui-test-junit4-android:1.7.8")
    // Manifes Testing untuk Compose
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.7.8")
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

