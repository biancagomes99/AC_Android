package br.com.biancabessa.ac02.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.biancabessa.ac02.R
import br.com.biancabessa.ac02.model.AddCadastro

class AddCadastroAdapter(
    val cad: List<AddCadastro>,
    val onClick: (AddCadastro) -> Unit

): RecyclerView.Adapter<AddCadastroAdapter.AddCadastroViewHolder>() {

    class AddCadastroViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardUsuario: TextView
        val cardSenha: TextView

        init {
            cardUsuario = view.findViewById(R.id.card_usuario)
            cardSenha = view.findViewById(R.id.card_senha)
        }
    }

    override fun getItemCount() = this.cad.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCadastroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cadastro, parent, false)

        val holder = AddCadastroViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: AddCadastroViewHolder, position: Int){
        val cad = cad[position]

        holder.cardUsuario.text = cad.usuario
        holder.cardSenha.text = cad.senha

        holder.itemView.setOnClickListener{onClick(cad)}

    }
}