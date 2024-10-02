package com.example.calculadorapekus.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.calculadorapekus.model.Calculator
import com.example.calculadorapekus.repository.CalculatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodasOperacoesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CalculatorRepository(application)

    private val listoperacoes = MutableLiveData<List<Calculator>>()
   val operacoesLiveData: LiveData<List<Calculator>> = listoperacoes

    suspend fun getAll() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.Main){
                    listoperacoes.value = repository.getTodasOperacoes()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    listoperacoes.value = emptyList()
                }
            }
        }
    }


    suspend fun deleteTodosCalculos() = repository.deleteTodosCalculos()

    suspend fun deleteCalculoPorId(id: Int) = repository.deleteCalculoPorId(id)



}