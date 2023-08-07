@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    id("kotlin-parcelize")
}

android {
    namespace = "com.gv.networkmodechanger"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.gv.networkmodechanger"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //TODO
        vectorDrawables {
            useSupportLibrary = true
        }
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
        freeCompilerArgs += "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        freeCompilerArgs += "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
        freeCompilerArgs += "-Xcontext-receivers"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)
    implementation(libs.kotlinxSerialization)

    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation(libs.ui.tooling)

    implementation(libs.lifecycleRuntime)
    implementation(libs.lifecycleViewModel)
    implementation(libs.lifecycleLiveData)

    implementation(libs.activity)
    implementation(libs.annotation)
    implementation(libs.appcompat)
    implementation(libs.biometric)
    implementation(libs.constraintlayout)
    implementation(libs.core.ktx)
    implementation(libs.core.splashScreen)
    implementation(libs.fragment)

    implementation(libs.recyclerView)
    implementation(libs.recyclerViewSelection)

    implementation(libs.roomRuntime)
    ksp(libs.roomCompiler)
    implementation(libs.room)
    implementation(libs.roomPaging)

    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)

    implementation(libs.paging)

    implementation(libs.viewPager2)

    implementation(libs.workManager)

    implementation(libs.material)

    implementation(libs.koin)

    implementation(libs.firebaseAnalytics)
    implementation(libs.firebaseCrashlytics)

    implementation(libs.googlePlayServicesAuth)
    implementation(libs.googleApiClient) {
        exclude(group = "org.apache.httpcomponents")
        exclude(module = "guava-jdk5")
    }
    implementation(libs.googleApiServicesDrive) {
        exclude(group = "org.apache.httpcomponents")
        exclude(module = "guava-jdk5")
    }
    implementation(libs.googleGuava)

    // LeakCanary
    //debugImplementation 'com.squareup.leakcanary:leakcanary-android:2.8.1'

    implementation(libs.androidx.test.core.ktx)
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.junit)
    implementation(libs.espresso.core)
    implementation(libs.kaspresso)
}