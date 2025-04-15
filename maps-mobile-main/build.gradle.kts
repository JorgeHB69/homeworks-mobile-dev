plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false
    alias(libs.plugins.ksp) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}