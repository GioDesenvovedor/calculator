package com.example.calculadorapekus.database



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.calculadorapekus.dao.CalculatorDao
import com.example.calculadorapekus.model.Calculator

@Database(entities = [Calculator::class], version = 1)
abstract class CalculatorDataBase : RoomDatabase() {

    abstract fun calculatorDao(): CalculatorDao

    companion object {
        @Volatile
        private var INSTANCE: CalculatorDataBase? = null

        fun getInstanceDatabase(context: Context): CalculatorDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CalculatorDataBase::class.java,
                    "calculator_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}