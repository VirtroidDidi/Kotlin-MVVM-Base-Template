package com.osvaldi.mvvm_demo.data

import com.osvaldi.mvvm_demo.model.User // Import corrigido para o novo pacote
import kotlinx.coroutines.delay

/**
 * Camada Data (Repository): É a fonte de verdade dos dados.
 * O MainViewModel depende desta classe.
 * NUNCA deve referenciar componentes Android (Activity, View, Context).
 * Simula uma chamada de rede (I/O).
 */
class MainRepository {

    /**
     * Simula o carregamento de um objeto User, como se viesse de um backend ou banco de dados.
     * @return O objeto User simulado.
     */
    suspend fun fetchUser(): User {
        // Simula o tempo de latência de uma chamada de rede ou de banco de dados
        delay(1500L)

        // Retorna um dado estático
        return User(
            id = 101,
            name = "Usuário MVVM (Carregado do Repositório)",
            isPremium = true
        )
    }
}
