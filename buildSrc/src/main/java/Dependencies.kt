object VersionAndroidConfig {
    var kotlin_version = "1.5.10"
    var android_sdk_version = 30
    var android_build_sdk_version = "30.0.3"
    var android_min_sdk_version = 21
    var app_version_code = 1
    var app_version_name = "1.0"
    var gradle_version = "4.2.1"
}

object  Libs{
    var kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${VersionAndroidConfig.kotlin_version}"
    var core_ktx = "androidx.core:core-ktx:1.3.1"
    var appcompat = "androidx.appcompat:appcompat:1.2.0"
    var material = "com.google.android.material:material:1.2.1"
    var constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.1"
}
object  TestLibs{
    var junit = "junit:junit:4.+"
}

object AndroidTestLibs{
   var junit = "androidx.test.ext:junit:1.1.2"
   var espresso_core = "androidx.test.espresso:espresso-core:3.3.0"
}