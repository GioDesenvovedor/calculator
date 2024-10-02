package com.example.calculadorapekus.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculos")
class Calculator {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "valorA")
    var valorA: Double = 0.0

    @ColumnInfo(name = "valorB")
    var valorB: Double = 0.0

    @ColumnInfo(name = "operacao")
    var operacao: String = ""

    @ColumnInfo(name = "resultado")
    var resultado: Double = 0.0

    @ColumnInfo(name = "dataHora")
    var dataHora: String = ""

}