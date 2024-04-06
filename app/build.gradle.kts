plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "fish.with.questbox"
    compileSdk = 34

    defaultConfig {
        applicationId = "fish.with.questbox"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    viewBinding {
        enable = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        jniLibs {
            excludes += listOf()
        }
        resources {
            excludes += listOf("META-INF/**", "kotlin/**", "okhttp3/**", "org/**", "**.properties", "**.bin", "**kotlin**")
        }
    }
}

dependencies {
    //implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //compileOnly(files("libs/oculus-internal-lib.jar"))

    implementation(project(":ocui"))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.preference:preference-ktx:1.2.1")

    implementation("com.google.android.material:material:1.11.0")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}