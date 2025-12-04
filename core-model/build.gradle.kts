plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.dalmuina.core.model"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
}

dependencies {

}