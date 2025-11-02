plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "vn.edu.ut.hieupm9898.homepageflow"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "vn.edu.ut.hieupm9898.homepageflow"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // navigation (Thư viện hỗ trợ điều hướng giữa các màn hình)
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Retrofit (Thư viện chính để gọi API)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson Converter (Biến đổi JSON thành Data Class)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp (Thư viện nền tảng cho Retrofit)
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")

    // Tích hợp ViewModel với Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")

    // Tích hợp StateFlow (Lifecycle) với Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.0")
}