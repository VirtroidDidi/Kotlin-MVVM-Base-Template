plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.osvaldi.mvvm_demo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.osvaldi.mvvm_demo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Dependências de UI
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity) // A versão 'activity.ktx' geralmente é preferida

    // -------------------------------------------------------------------------
    // DEPENDÊNCIAS ESSENCIAIS MVVM & COROUTINES (JETPACK)
    // -------------------------------------------------------------------------

    // Componentes de ViewModel (Gerencia o estado da UI e sobrevive a rotações)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Componentes de LiveData (Para dados observáveis, notificando a View)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Coroutines (Para gerenciamento assíncrono - ex: chamada de API simulada no Repository)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Extensões KTX para Activities e Fragments (simplifica a inicialização do ViewModel)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.activity.ktx)

    // Opcional, mas útil: KTX para o Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Dependências de Teste
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}