package com.osvaldi.mvvm_demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osvaldi.mvvm_demo.data.MainRepository

/**
 * Factory necessário para instanciar o MainViewModel que possui dependências no construtor.
 * Em projetos maiores, este passo seria automatizado por Hilt ou Koin.
 */
class MainViewModelFactory(
    private val repository: MainRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            // Instancia o MainViewModel passando o Repository como dependência.
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
