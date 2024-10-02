package com.example.calculadorapekus.viewHolder

import android.app.AlertDialog
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.calculadorapekus.databinding.ItemOperacoesBinding
import com.example.calculadorapekus.listner.OperacoesListner
import com.example.calculadorapekus.model.Calculator

class OpercaoViewHolder(
    private val itemVi: ItemOperacoesBinding, private val listener: OperacoesListner
): RecyclerView.ViewHolder(itemVi.root) {

    fun bindViewHolder(item: Calculator){

        itemVi.txtId.text = item.id.toString()
        itemVi.txtValorA.text = item.valorA.toString()
        itemVi.txtValorB.text = item.valorB.toString()
        itemVi.txtResultado.text = item.resultado.toString()
        itemVi.txtOperacao.text = item.operacao.toString()
        itemVi.txtData.text = item.dataHora.toString()


        //Evento de click no card da lista
        itemVi.card.setOnClickListener {
            listener.onClick(item.id)

        }
        itemVi.card.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Atenção")
                .setMessage("Deseja excluir o item?")
                .setPositiveButton("Sim") { _, _ ->
                    listener.OnDelete(item.id)
                }
                .setNegativeButton("Não", null)
                .show()
            true
        }
    }

}