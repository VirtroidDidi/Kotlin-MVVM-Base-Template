package com.osvaldi.mvvm_demo.model

/**
 * Camada Model: Representa a estrutura de dados.
 * É uma 'data class' simples, imutável e puramente sobre o domínio de dados.
 * A View e o ViewModel dependem dela.
 */
data class User(
    val id: Int,
    val name: String,
    val isPremium: Boolean = false
)
