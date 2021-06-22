object VersionAndroidConfig {
    val kotlin_version = "1.5.10"
    val android_sdk_version = 30
    val android_build_sdk_version = "30.0.3"
    val android_min_sdk_version = 21
    val app_version_code = 1
    val app_version_name = "1.0"
    val gradle_version = "4.2.1"
}

object  Libs{
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${VersionAndroidConfig.kotlin_version}"
    val core_ktx = "androidx.core:core-ktx:1.3.1"
    val appcompat = "androidx.appcompat:appcompat:1.2.0"
    val material = "com.google.android.material:material:1.2.1"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.1"
}
object  TestLibs{
    val junit = "junit:junit:4.+"
}

object AndroidTestLibs{
    val junit = "androidx.test.ext:junit:1.1.2"
    val espresso_core = "androidx.test.espresso:espresso-core:3.3.0"
}