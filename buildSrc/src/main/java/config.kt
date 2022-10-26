

object Maven{
   const val jitpackIOUrl = "https://jitpack.io"
}

object GradlePlugins {
   const val androidApplication = "com.android.application"
   const val android = "'org.jetbrains.kotlin.android'"
   const val kotlin = "kotlin"
   const val kotlinAndroid = "kotlin-android"
   const val kotlinParcelize = "kotlin-parcelize"
   const val kotlinKapt = "kotlin-kapt"
   const val javaLib = "java-library"
   const val androidLib = "com.android.library"
   const val navigationSafeArgs = "androidx.navigation.safeargs"
}

object Android {
   const val minSdk = 21
   const val compileSdk = 33
   const val targetSdk = 33
   const val versionCode = 1
   const val versionName = "1.0"
}

const val compose_version = "1.2.0"

object AndroidX{
   const val core = "androidx.core:core-ktx:1.9.0"
   const val accompanist_insets = "com.google.accompanist:accompanist-insets:0.18.0"
   const val accompanist_systemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:0.18.0"
   const val accompanist_coil = "com.google.accompanist:accompanist-coil:0.15.0"
}
object AndroidTest{
   const val junit = "junit:junit:4.13.2"
   const val ext = "androidx.test.ext:junit:1.1.3"
   const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
   const val compose = "androidx.compose.ui:ui-test-junit4:$compose_version"
}
object Kotlin{
   const val kotlinJvmTarget ="1.8"
   private const val kotlin_version ="1.6.4"
   const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_version"
   const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_version"
}

object Jetpack{
   private const val lifecycle_version ="2.5.1"
   private const val datastore_version ="1.0.0"
   const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
   const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
   const val lifecycle_viewmodel= "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
   const val datastore_preferences= "androidx.datastore:datastore-preferences:$datastore_version"
   const val datastore_core= "androidx.datastore:datastore-core:$datastore_version"
   const val startup= "androidx.startup:startup-runtime:1.2.0-alpha01"
}

object Compose{
   const val activity = "androidx.activity:activity-compose:1.3.1"
   const val ui = "androidx.compose.ui:ui:${compose_version}"
   const val material = "androidx.compose.material:material::${compose_version}"
   const val ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview:${compose_version}"
   const val ui_tooling = "androidx.compose.ui:ui-tooling:${compose_version}"
   const val ui_test_manifest = "androidx.compose.ui:ui-tooling:${compose_version}"
}

object Other{
   const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.9.1"
   private const val coil_version = "2.1.0"
   const val coil = "io.coil-kt:coil:$coil_version"
   const val coil_compose = "io.coil-kt:coil-compose:$coil_version"
   const val okhttp3 = "com.squareup.okhttp3:okhttp:4.10.0"
   private const val rxhttp_version = "2.9.3"
   const val rxhttp = "com.github.liujingxing.rxhttp:rxhttp:$rxhttp_version"
   const val rxhttp_compiler = "com.github.liujingxing.rxhttp:rxhttp-compiler:$rxhttp_version"
}