import Versions.ANDROIDX_VERSION
import Versions.CONSTRAINT_LAYOUT_VERSION
import Versions.CORE_KTX_VERSION
import Versions.COROUTINES_VERSION
import Versions.GLIDE_VERSION
import Versions.KOIN_VERSION
import Versions.KOTLIN_STD_LIB_VERSION
import Versions.MATERIAL_VERSION
import Versions.OKHTTP_3_INTERCEPTOR_VERSION
import Versions.RETROFIT_VERSION
import Versions.ROOM_VERSION
import Versions.RX_LAVA_VERSION
import org.gradle.api.JavaVersion

object Config {
    const val APPLICATION_ID = "ru.brauer.cleanarchitecture"
    const val COMPILE_SDK = 31
    const val BUILD_TOOLS_VERSION = "31"
    const val MIN_SDK = 23
    const val TARGET_SDK = 31
    val JAVA_VERSION = JavaVersion.VERSION_1_8
    const val JVM_TARGET = "1.8"
}

object Releases {
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
}

object Versions {
    const val ANDROIDX_VERSION = "1.4.0"
    const val MATERIAL_VERSION = "1.4.0"
    const val CONSTRAINT_LAYOUT_VERSION = "2.1.2"
    const val CORE_KTX_VERSION = "1.7.0"
    const val COROUTINES_VERSION = "1.5.2"
    const val KOIN_VERSION = "3.1.4"
    const val KOTLIN_STD_LIB_VERSION = "1.6.0"
    const val RX_LAVA_VERSION = "3.0.0"
    const val GLIDE_VERSION = "4.12.0"
    const val ROOM_VERSION = "2.3.0"
    const val RETROFIT_VERSION = "2.9.0"
    const val OKHTTP_3_INTERCEPTOR_VERSION = "4.9.0"
}

object Dependencies {

    const val ANDROIDX_DEP = "androidx.appcompat:appcompat:$ANDROIDX_VERSION"

    //Design
    const val MATERIAL_DEP = "com.google.android.material:material:$MATERIAL_VERSION"
    const val CONSTRAINT_LAYOUT_DEP =
        "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"

    //Kotlin
    const val CORE_KTX_DEP = "androidx.core:core-ktx:$CORE_KTX_VERSION"
    const val KOTLIN_STD_LIB_DEP =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$KOTLIN_STD_LIB_VERSION"

    //Kotlin Coroutines
    const val COROUTINES_CORE_DEP =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION"
    const val COROUTINES_ANDROID_DEP =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"

    //Rx-Java
    const val RX_JAVA_CORE_DEP = "io.reactivex.rxjava3:rxjava:$RX_LAVA_VERSION"
    const val RX_JAVA_ANDROID_DEP = "io.reactivex.rxjava3:rxandroid:$RX_LAVA_VERSION"

    //Retrofit
    const val RETROFIT_2_CORE_DEP = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val RETROFIT_2_CONVERTER_GSON_DEP =
        "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
    const val OKHTTP_INTERCEPTOR_DEP =
        "com.squareup.okhttp3:logging-interceptor:$OKHTTP_3_INTERCEPTOR_VERSION"
    const val RETROFIT_RX_JAVA_ADAPTER_DEP =
        "com.github.akarnokd:rxjava3-retrofit-adapter:$RX_LAVA_VERSION"

    //Koin
    const val KOIN_CORE_DEP = "io.insert-koin:koin-core:$KOIN_VERSION"
    const val KOIN_ANDROID_DEP = "io.insert-koin:koin-android:$KOIN_VERSION"
    const val KOIN_ANDROID_COMPAT_DEP = "io.insert-koin:koin-android-compat:$KOIN_VERSION"

    //Room
    const val ROOM_DEP = "androidx.room:room-runtime:$ROOM_VERSION"
    const val ROOM_KAPT_DEP = "androidx.room:room-compiler:$ROOM_VERSION"

    //Glide
    const val GLIDE_DEP = "com.github.bumptech.glide:glide:$GLIDE_VERSION"
}