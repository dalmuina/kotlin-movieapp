plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.dalmuina.di"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":design-system"))
    implementation(project(":feature-movies"))
    implementation(project(":data"))
    implementation(project(":domain"))
    // Koin
    implementation(libs.koin.android)

    implementation(libs.androidx.core.ktx)

    implementation(libs.ktor.client.okhttp)
}