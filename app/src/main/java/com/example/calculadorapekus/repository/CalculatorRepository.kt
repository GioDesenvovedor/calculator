package com.example.calculadorapekus.repository


import android.app.Application
import com.example.calculadorapekus.dao.CalculatorDao
import com.example.calculadorapekus.database.CalculatorDataBase

import com.example.calculadorapekus.model.Calculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CalculatorRepository(application: Application) {
    private val calculatorDataBase =
        CalculatorDataBase.getInstanceDatabase(application).calculatorDao()

    suspend fun insert(calculator: Calculator) = calculatorDataBase.insert(calculator)

    suspend fun getTodasOperacoes():
            List<Calculator> = withContext(Dispatchers.IO) {
        try {
            return@withContext calculatorDataBase.getTodasOperacoes()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext emptyList<Calculator>()

    }


    suspend fun getCalculoById(id: Int) = calculatorDataBase.getCalculoById(id)

    suspend fun deleteTodosCalculos() = calculatorDataBase.deleteTodosCalculos()

    suspend fun deleteCalculoPorId(id: Int) = calculatorDataBase.deleteCalculoPorId(id)


}