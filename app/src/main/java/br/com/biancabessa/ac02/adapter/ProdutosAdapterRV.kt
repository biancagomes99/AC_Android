package br.com.biancabessa.ac02.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.biancabessa.ac02.R
import br.com.biancabessa.ac02.model.ProdutoClasse
import kotlinx.android.synthetic.main.item_lista_de_produtos.view.*

class ProdutosAdapterRV(
    val listaDeProdutos: List<ProdutoClasse>,
    val onClick: (ProdutoClasse) -> Unit
): RecyclerView.Adapter<ProdutosAdapterRV.ViewHolderProduto>() {

    class ViewHolderProduto(view: View): RecyclerView.ViewHolder(view) {
        val textView_produto: TextView
        val cardView: CardView

        init {
            textView_produto = view.findViewById(R.id.card_nome)
            cardView = view.findViewById(R.id.card_produtos)
        }
    }

    override fun getItemCount() = this.listaDeProdutos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProduto {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista_de_produtos, parent, false)

        val holder = ViewHolderProduto(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolderProduto, position: Int) {
        val produtos = listaDeProdutos[position]

        holder.textView_produto.text = produtos.nome

        holder.itemView.setOnClickListener{onClick(produtos)}
    }
}