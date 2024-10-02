package com.example.calculadorapekus.viewModel



import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.calculadorapekus.model.Calculator
import com.example.calculadorapekus.repository.CalculatorRepository



class CalculatorViewModel(application: Application): AndroidViewModel(application)  {

    private val repository = CalculatorRepository(application)

    private val _todosCalculos = MutableLiveData<List<Calculator>>()
    val todosCalculos: LiveData<List<Calculator>> = _todosCalculos



    suspend fun insert(calculator: Calculator) {

        try{
            repository.insert(calculator)
        }catch (e: Exception){
            e.printStackTrace()
            Log.e("Erro", e.message.toString())
        }

    }

    suspend fun getAll() = repository.getTodasOperacoes()

    suspend fun getCalculoById(id: Int) = repository.getCalculoById(id)

    suspend fun deleteTodosCalculos() = repository.deleteTodosCalculos()

}