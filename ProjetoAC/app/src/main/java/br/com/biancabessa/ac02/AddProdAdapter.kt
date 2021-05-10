package br.com.biancabessa.ac02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AddProdAdapter(
        val produtos: List<AddProd>,
        val onClick: (AddProd) -> Unit

): RecyclerView.Adapter<AddProdAdapter.AddProdViewHolder>() {

    class AddProdViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardNome: TextView

        init {
            cardNome = view.findViewById(R.id.card_nome)
        }
    }

    override fun getItemCount() = this.produtos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProdViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_prod, parent, false)

        val holder = AddProdViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: AddProdViewHolder, position: Int){
        val prod = produtos[position]

        holder.cardNome.text = prod.nome
        holder.itemView.setOnClickListener{onClick(prod)}

    }
}