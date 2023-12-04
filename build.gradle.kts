buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}
plugins {
    id("com.android.application") version "8.2.0" apply false
}