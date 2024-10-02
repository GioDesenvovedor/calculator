package com.example.calculadorapekus.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculadorapekus.databinding.ItemOperacoesBinding
import com.example.calculadorapekus.listner.OperacoesListner
import com.example.calculadorapekus.model.Calculator
import com.example.calculadorapekus.viewHolder.OpercaoViewHolder

class OperacoesAdapter: RecyclerView.Adapter<OpercaoViewHolder>() {

    private var listaOperacoes: List<Calculator> = listOf()
    private lateinit var listner: OperacoesListner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpercaoViewHolder {
       val item = ItemOperacoesBinding.inflate(
           LayoutInflater.from(parent.context), parent, false)

        return OpercaoViewHolder(item, listner)
    }

    override fun onBindViewHolder(holder: OpercaoViewHolder, position: Int) {
        holder.bindViewHolder(listaOperacoes[position])
    }

    override fun getItemCount(): Int {
        return listaOperacoes.size
        Log.i("GetCount", "contando${listaOperacoes.size}: ")
    }



    fun updateOperacoes(list: List<Calculator>) {
        listaOperacoes = list
        notifyDataSetChanged()

    }
    fun attachListener(operacoesListner: OperacoesListner) {
        listner = operacoesListner

    }
}