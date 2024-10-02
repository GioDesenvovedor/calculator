package com.example.calculadorapekus.dao

import android.app.Application
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.calculadorapekus.model.Calculator

@Dao
interface CalculatorDao {
    @Insert
    suspend fun insert(calculator: Calculator)

    @Query("SELECT * FROM calculos WHERE id = :id")
    suspend fun getCalculoById(id: Int): Calculator?

    @Query("SELECT * FROM calculos")
    fun getTodasOperacoes(): List<Calculator>

    @Query("DELETE FROM calculos")
    fun deleteTodosCalculos(): Int

    @Query("DELETE FROM calculos WHERE id = :id")
    fun deleteCalculoPorId(id: Int): Int

}