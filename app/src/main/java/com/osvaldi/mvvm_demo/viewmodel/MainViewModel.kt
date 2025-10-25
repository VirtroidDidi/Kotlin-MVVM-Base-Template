package com.osvaldi.mvvm_demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osvaldi.mvvm_demo.data.MainRepository // Import corrigido
import com.osvaldi.mvvm_demo.model.User // Import corrigido
import kotlinx.coroutines.launch

/**
 * Camada ViewModel: Contém a lógica de apresentação e mantém o estado da UI.
 * Sobrevive a mudanças de configuração (rotação).
 * A View (MainActivity) depende desta classe.
 */
class MainViewModel(
    private val repository: MainRepository // Dependência injetada
) : ViewModel() {

    // MutableLiveData (privado): Usado internamente para atualizar o estado.
    private val _user = MutableLiveData<User>()

    // LiveData (público): Exposto para a View observar. Imutável por fora.
    val user: LiveData<User> = _user

    // Estado de Carregamento para melhorar a experiência do usuário
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        // Estado inicial
        _user.value = User(id = -1, name = "Clique no botão para carregar o usuário...")
    }

    /**
     * Função que inicia a chamada de dados no escopo do ViewModel.
     * Esta função é chamada pela View (Activity).
     */
    fun loadUser() {
        // Inicia uma Coroutine vinculada ao ciclo de vida do ViewModel
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // 1. Chama o Repository (Data Layer)
                val fetchedUser = repository.fetchUser()
                // 2. Atualiza o LiveData, o que notifica a View (Activity)
                _user.value = fetchedUser
            } catch (e: Exception) {
                // Trata erros (em um app real, você atualizaria um LiveData de erro)
                _user.value = User(id = 0, name = "Erro ao carregar dados.")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
