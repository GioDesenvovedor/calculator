package com.example.calculadorapekus.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculadorapekus.R
import com.example.calculadorapekus.adapter.OperacoesAdapter
import com.example.calculadorapekus.databinding.ActivityHistoricoOperacoesBinding
import com.example.calculadorapekus.listner.OperacoesListner
import com.example.calculadorapekus.viewModel.TodasOperacoesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoricoOperacoes : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricoOperacoesBinding

    private val viewModel: TodasOperacoesViewModel by viewModels()

    private val adapterOper = OperacoesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityHistoricoOperacoesBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initAdapter()
        chamadaViewModel()

    }

    private fun chamadaViewModel() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getAll()
            withContext(Dispatchers.Main){
                observe()
            }
        }
    }

    private fun initAdapter() {
        Log.i("teste", "initAdapter: ")
        binding.listOperacoes.layoutManager = LinearLayoutManager(this)
        binding.listOperacoes.adapter = adapterOper

        val listner = object : OperacoesListner {
            override fun onClick(id: Int) {
                Toast.makeText(applicationContext, "Poderia fazer algo com o id: $id", Toast.LENGTH_SHORT).show()
            }

            override fun OnDelete(id: Int) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.deleteCalculoPorId(id)
                }
            }
        }
        adapterOper.attachListener(listner)

    }



    private fun observe() {
        viewModel.operacoesLiveData.observe(this) {
            val listaInverida = it.reversed() //Inverte a lista o ultimo registro fica em primeiro
            adapterOper.updateOperacoes(listaInverida)// Atualiza o adapter com a lista invertida
        }
    }

}