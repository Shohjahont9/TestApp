@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "uz.larslion.testapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "uz.larslion.testapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// FOR COMPOSE DESTINATION
kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}


dependencies {
    implementation("androidx.core:core-ktx:${rootProject.extra["core_ktx_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${rootProject.extra["lifecycle_version"]}")
    implementation("androidx.activity:activity-compose:${rootProject.extra["compose_activity_version"]}")
    implementation(platform("androidx.compose:compose-bom:${rootProject.extra["bom_version"]}"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:${rootProject.extra["unit_test_version"]}")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:${rootProject.extra["bom_version"]}"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    //Gson and retrofit
    implementation("com.google.code.gson:gson:${rootProject.extra["retrofit_version"]}")
    implementation("com.squareup.retrofit2:retrofit:${rootProject.extra["retrofit_version"]}")
    implementation("com.squareup.retrofit2:converter-gson:${rootProject.extra["retrofit_version"]}")
    implementation("com.squareup.retrofit2:converter-scalars:${rootProject.extra["retrofit_version"]}")
    implementation("com.squareup.okhttp3:logging-interceptor:${rootProject.extra["logging_version"]}")
    // Network Adapter
    implementation("com.github.haroldadmin:NetworkResponseAdapter:${rootProject.extra["harold_version"]}")
    // Navigation raamcosta
    implementation("io.github.raamcosta.compose-destinations:core:${rootProject.extra["raamcosta_version"]}")
    ksp("io.github.raamcosta.compose-destinations:ksp:${rootProject.extra["raamcosta_version"]}")
    // hilt
    implementation("com.google.dagger:hilt-android:${rootProject.extra["hilt_version"]}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.extra["hilt_version"]}")
    implementation("androidx.hilt:hilt-navigation-compose:${rootProject.extra["hilt_navigation_version"]}")
    //Coil
    implementation("io.coil-kt:coil-compose:${rootProject.extra["coil_version"]}")

}