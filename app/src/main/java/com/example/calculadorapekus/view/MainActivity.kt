package com.example.calculadorapekus.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadorapekus.R
import com.example.calculadorapekus.databinding.ActivityMainBinding
import com.example.calculadorapekus.model.Calculator
import com.example.calculadorapekus.viewModel.CalculatorViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {


    private val viewM: CalculatorViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnSoma.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var operacao = "+"
                somar(operacao)
            }
        }

        binding.btnSubtracao.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var operacao = "-"
                subtrair(operacao)
            }
        }
        binding.btnMultiplicacao.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var operacao = "*"
                multiplicar(operacao)
            }
        }
        binding.btnDivisao.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var operacao = "/"
                dividir(operacao)
            }
        }

        historico()
    }

    private fun historico() {
        binding.historicoOperacao.setOnClickListener {
            val intent = Intent(this, HistoricoOperacoes::class.java)
            startActivity(intent)
        }
    }

    private suspend fun dividir(operacao: String) {
        var primeiro = binding.valorA.text.toString().toDoubleOrNull()
        var segundo = binding.valorB.text.toString().toDoubleOrNull()

        if (primeiro != null && segundo != null && segundo != 0.0) {

            val resultado = primeiro / segundo

            val resultFormatado = if (resultado % 1 == 0.0) {
                resultado.toInt().toString()
            } else {
                resultado.toString()
            }
            withContext(Dispatchers.Main) {
                binding.textResultado.text = resultFormatado
            }

            insert(operacao)


        } else {
            val snackbar =
                Snackbar.make(binding.liner, "Veirifque se digitou os campos A e B, O campo B não pode ser 0", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }


    private suspend fun multiplicar(operacao: String) {
        var primeiro = binding.valorA.text.toString().toDoubleOrNull()
        var segundo = binding.valorB.text.toString().toDoubleOrNull()

        if (primeiro != null && segundo != null) {
            val resultado = primeiro * segundo

            val resultFormatado = if (resultado % 1 == 0.0) {
                resultado.toInt().toString()
            } else {
                resultado.toString()
            }
            withContext(Dispatchers.Main) {
                binding.textResultado.text = resultFormatado
            }

            insert(operacao)


        } else {
            val snackbar =
                Snackbar.make(binding.liner, "Preencha os campos A e B", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }

    private suspend fun subtrair(operacao: String) {
        var primeiro = binding.valorA.text.toString().toDoubleOrNull()
        var segundo = binding.valorB.text.toString().toDoubleOrNull()

        if (primeiro != null && segundo != null) {
            val resultado = primeiro - segundo

            val resultFormatado = if (resultado % 1 == 0.0) {
                resultado.toInt().toString()
            } else {
                resultado.toString()
            }
            withContext(Dispatchers.Main) {
                binding.textResultado.text = resultFormatado
            }

            insert(operacao)


        } else {
            val snackbar =
                Snackbar.make(binding.liner, "Preencha os campos A e B", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }


    suspend fun somar(operacao: String) {

        var primeiro = binding.valorA.text.toString().toDoubleOrNull()
        var segundo = binding.valorB.text.toString().toDoubleOrNull()
        var operacao = operacao

        if (primeiro != null && segundo != null) {
            val resultado = primeiro + segundo

            val resultFormatado = if (resultado % 1 == 0.0) {
                resultado.toInt().toString()
            } else {
                resultado.toString()
            }
            withContext(Dispatchers.Main) {
                binding.textResultado.text = resultFormatado
            }

            insert(operacao)


        } else {
            val snackbar =
                Snackbar.make(binding.liner, "Preencha os campos A e B", Snackbar.LENGTH_LONG)
            snackbar.show()
        }


    }

    private suspend fun insert(operacao: String) {
        var primeiro = binding.valorA.text.toString().toDoubleOrNull()
        var segundo = binding.valorB.text.toString().toDoubleOrNull()
        var resultado = binding.textResultado.text.toString().toDoubleOrNull()
        var operacao = operacao

        if (primeiro != null && segundo != null && resultado != null) {
            val model = Calculator().apply {
                this.valorA = primeiro
                this.valorB = segundo
                this.operacao = operacao
                this.resultado = resultado


                // Formata a data e hora atual para armazenar
                this.dataHora =
                    SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())

            }
            withContext(Dispatchers.IO) {
                viewM.insert(model)

            }
            // Limpa os campos de entrada
            withContext(Dispatchers.Main) {

                val snackbar =
                    Snackbar.make(
                        binding.liner,
                        "Cálculo armazenado com sucesso",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.show()
                binding.valorB.text?.clear()
                binding.valorA.text?.clear()
            }

        }


    }


}