plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {

    compileSdk = 31
    buildToolsVersion = "31"

    defaultConfig {
        applicationId = "ru.brauer.cleanarchitecture"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //AndroidX
    implementation(Dependencies.ANDROIDX_DEP)

    //Design
    implementation(Dependencies.MATERIAL_DEP)
    implementation(Dependencies.CONSTRAINT_LAYOUT_DEP)

    //Kotlin
    implementation(Dependencies.CORE_KTX_DEP)
    implementation(Dependencies.KOTLIN_STD_LIB_DEP)

    //Kotlin Coroutines
    implementation(Dependencies.COROUTINES_CORE_DEP)
    implementation(Dependencies.COROUTINES_ANDROID_DEP)

    //Rx-Java
    implementation(Dependencies.RX_JAVA_CORE_DEP)
    implementation(Dependencies.RX_JAVA_ANDROID_DEP)

    //Retrofit
    implementation(Dependencies.RETROFIT_2_CORE_DEP)
    implementation(Dependencies.RETROFIT_2_CONVERTER_GSON_DEP)
    implementation(Dependencies.OKHTTP_INTERCEPTOR_DEP)
    implementation(Dependencies.RETROFIT_RX_JAVA_ADAPTER_DEP)

    //Koin
    implementation(Dependencies.KOIN_CORE_DEP)
    implementation(Dependencies.KOIN_ANDROID_DEP)
    implementation(Dependencies.KOIN_ANDROID_COMPAT_DEP)

    //Room
    implementation(Dependencies.ROOM_DEP)
    kapt(Dependencies.ROOM_KAPT_DEP)

    //Glide
    implementation(Dependencies.GLIDE_DEP)

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}