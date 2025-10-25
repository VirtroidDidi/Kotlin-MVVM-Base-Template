# Kotlin-MVVM-Base-Template

[![Android CI](https://github.com/VirtroidDidi/Kotlin-MVVM-Base-Template/actions/workflows/android.yml/badge.svg)](https://github.com/VirtroidDidi/Kotlin-MVVM-Base-Template/actions/workflows/android.yml)


## 🎯 Objetivo do Projeto

Este projeto serve como um modelo (Base Template) minimalista e robusto para o desenvolvimento de
aplicações Android utilizando **Kotlin** e a arquitetura **MVVM (Model-View-ViewModel)**. O foco é
demonstrar a correta aplicação do **Princípio da Responsabilidade Única (SoCs)** e o gerenciamento
do ciclo de vida da UI.

## 🧱 Arquitetura e Componentes Jetpack

A estrutura do projeto é rigorosamente dividida em camadas, conforme o guia de arquitetura Android:

| Camada                | Responsabilidade                                                                 | Tecnologia(s) Utilizada(s)                                             |
|:----------------------|:---------------------------------------------------------------------------------|:-----------------------------------------------------------------------|
| **View (UI)**         | Display de dados e captura de eventos do usuário.                                | `MainActivity` (AppCompatActivity), View Binding.                      |
| **ViewModel**         | Lógica de apresentação, estado da UI e sobrevivência a mudanças de configuração. | **`ViewModel`** (Androidx), **`LiveData`** (para comunicação reativa). |
| **Data (Repository)** | Fonte de verdade. Simula o acesso a dados remotos/locais.                        | `MainRepository` (Kotlin class).                                       |
| **Domain (Model)**    | Estruturas de dados puras e imutáveis.                                           | `User` (Kotlin `data class`).                                          |

### Principais Destaques Técnicos:

1. **Reatividade com LiveData:** O `MainActivity` observa o estado (`User` e `isLoading`) via
   `LiveData`, garantindo que a UI reaja automaticamente às mudanças de estado, sem a necessidade de
   manipulação manual da View.
2. **Coroutines (Assincronismo):** O `MainViewModel` utiliza o `viewModelScope.launch` para invocar
   o `Repository`, garantindo que operações de I/O (simuladas por `delay(1500L)`) ocorram em threads
   de fundo, sem bloquear a UI.
3. **Injeção de Dependência Manual:** A `MainViewModelFactory` é usada para injetar o
   `MainRepository` no `MainViewModel`, desacoplando as camadas e facilitando a testabilidade (
   substituindo o Repository por um Mock em testes).

## 🗂️ Estrutura do Projeto

com.osvaldi.mvvm_demo/

├── data/

│ └── MainRepository.kt (Fonte de Dados)

├── model/

│ └── User.kt (Data Class)

├── ui/

│ └── MainActivity.kt (A View - Observador)

└── viewmodel/

├── MainViewModel.kt (Lógica/Estado)

└── MainViewModelFactory.kt (DI Manual)

## 🛠️ Como Executar

1. Clone o repositório.
2. Abra no Android Studio (Giraffe+ recomendado).
3. Sincronize o Gradle e execute em um emulador ou dispositivo.