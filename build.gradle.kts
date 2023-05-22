// Top-level build file where you can add configuration options common to all sub-projects/modules.
ext {
    extra["core_ktx_version"] = "1.10.1"
    extra["lifecycle_version"] = "2.6.1"
    extra["compose_activity_version"] = "1.7.1"
    extra["bom_version"] = "2023.05.01"
    extra["unit_test_version"] = "4.13.2"
    extra["raamcosta_version"] = "1.9.42-beta"
    extra["harold_version"] = "5.0.0"
    extra["retrofit_version"] = "2.9.0"
    extra["logging_version"] = "5.0.0-alpha.1"
    extra["hilt_version"] = "2.44"
    extra["hilt_navigation_version"] = "1.1.0-alpha01"
    extra["coil_version"] = "2.0.0-rc01"
}


plugins {
    id("com.android.application") version "8.0.1" apply false
    id("com.android.library") version "8.0.1" apply false
    id("com.google.devtools.ksp") version "1.8.21-1.0.11" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}