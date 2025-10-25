package com.osvaldi.mvvm_demo.ui // Pacote UI (View)

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider // Novo import adicionado
import com.osvaldi.mvvm_demo.data.MainRepository // Import corrigido
import com.osvaldi.mvvm_demo.databinding.ActivityMainBinding
import com.osvaldi.mvvm_demo.viewmodel.MainViewModel // Import corrigido
import com.osvaldi.mvvm_demo.viewmodel.MainViewModelFactory // Import corrigido

/**
 * Camada View: Responsável por exibir a UI e capturar eventos do usuário.
 * Ela observa o LiveData do ViewModel e reage às mudanças.
 * NUNCA acessa o Repository diretamente.
 */
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialização do ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory(MainRepository())
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        // Chamada da lógica MVVM
        setupListeners()
        setupObservers()
    }

    /**
     * Configura os Listeners para interações do usuário.
     * Ações do usuário vão DA View PARA o ViewModel.
     */
    private fun setupListeners() {
        binding.btnLoadUser.setOnClickListener {
            // Ação do usuário: chama a função no ViewModel.
            // A View não sabe como ou de onde o dado será carregado.
            viewModel.loadUser()
        }
    }

    /**
     * Configura os Observers para o LiveData do ViewModel.
     * Fluxo de Dados: DO ViewModel PARA a View (Reação).
     */
    private fun setupObservers() {
        // Observa o estado do Usuário.
        viewModel.user.observe(this) { user ->

            if (user.id == -1) {
                binding.tvUserName.text = user.name
                binding.tvUserName.textSize = 18f

            } else {
                // ESTADO DE DADOS CARREGADOS: Aplica a formatação visual
                val premiumStatus = if (user.isPremium) "✅ Usuário PREMIUM" else "Usuário Padrão"

                val formattedText =
                    "**CARREGAMENTO CONCLUÍDO**\n\n" +
                            "👤 **Nome:** ${user.name}\n" +
                            "🆔 **ID:** ${user.id}\n\n" +
                            "✨ **Status:** $premiumStatus"

                // Aplica a formatação
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    binding.tvUserName.text = android.text.Html.fromHtml(
                        formattedText.replace("**", "<b>"),
                        android.text.Html.FROM_HTML_MODE_LEGACY
                    )
                } else {
                    @Suppress("DEPRECATION")
                    binding.tvUserName.text =
                        android.text.Html.fromHtml(formattedText.replace("**", "<b>"))
                }
                binding.tvUserName.textSize = 20f // Texto ligeiramente maior para o resultado
            }
            // Garante que o TextView está sempre visível quando o LiveData do usuário é atualizado
            binding.tvUserName.visibility = View.VISIBLE
        }

        // Observa o estado de carregamento para feedback visual.
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnLoadUser.isEnabled = !isLoading // Desabilita o botão enquanto carrega

            // Oculta o texto quando está carregando (melhora a UX)
            if (isLoading) {
                binding.tvUserName.visibility = View.GONE
            }
        }
    }

}