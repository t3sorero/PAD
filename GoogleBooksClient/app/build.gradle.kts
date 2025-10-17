import java.util.Properties
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "es.ucm.fdi.pad.googlebooksclient"
    compileSdk = 36

    defaultConfig {
        applicationId = "es.ucm.fdi.pad.googlebooksclient"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //LEER API_KEY DESDE local.properties

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "GOOGLE_BOOKS_API_KEY", "\"${properties.getProperty("GOOGLE_BOOKS_API_KEY")}\"")
    }

    buildFeatures {

        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.recyclerview)
    implementation(libs.cardview)
    implementation(libs.appcompat.v170)
}