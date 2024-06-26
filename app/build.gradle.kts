import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.jbrunoo.seoul_go"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jbrunoo.seoul_go"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        manifestPlaceholders["kakao_native_app_key"] = "KAKAO_NATIVE_APP_KEY"
        buildConfigField("String", "SEOUL_API_KEY", getProperty("SEOUL_API_KEY"))
        buildConfigField("String", "BASE_URL", getProperty("BASE_URL"))
        buildConfigField("String", "SEOUL_GO_BASE_URL", getProperty("SEOUL_GO_BASE_URL"))
        buildConfigField("String", "GOOGLE_CLIENT_ID", getProperty("GOOGLE_CLIENT_ID"))
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", getProperty("KAKAO_NATIVE_APP_KEY"))

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun getProperty(property: String): String {
    return gradleLocalProperties(rootDir).getProperty(property)
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
    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    // room
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
    // retrofit & kotlin-serialization
    implementation(libs.bundles.retrofit)
    // viewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // navigation
    implementation(libs.androidx.navigation.compose)
    // coil
    implementation(libs.coil.compose)
    // material
    implementation(libs.androidx.material)
    // workManager // Kotlin + coroutines
    implementation(libs.androidx.work.runtime.ktx)
    // timber(logger)
    implementation(libs.timber)
    // collectAsStateWithLifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)
    // lottie
    implementation(libs.lottie.compose)
    // Google Credential Manager(authentication)
    implementation(libs.bundles.gooleCredentialManager)
    // authorization
    implementation(libs.play.services.auth)
    // kakao login
    implementation(libs.v2.user)
    // datastore(preferences)
    implementation(libs.androidx.datastore.preferences)
}