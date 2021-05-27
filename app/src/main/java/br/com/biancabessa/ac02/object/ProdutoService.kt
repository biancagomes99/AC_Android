package br.com.biancabessa.ac02.`object`

import br.com.biancabessa.ac02.model.ProdutoClasse

object ProdutoService {

    /*fun getProduto (): List<ProdutoClasse>{
        var produtos = mutableListOf<ProdutoClasse>()

        for (i in 1..10){
            val p = ProdutoClasse()
            p.nome = "Produto $i"

            produtos.add(p)
        }

        return produtos
    }*/

    fun getProdutosDB(): List<ProdutoClasse>{
        val daoprod = DatabaseManager.getProdutoDAO()
        return daoprod.findAll()
    }

    fun saveProdutoDB(produto: ProdutoClasse): String{
        val daoprod = DatabaseManager.getProdutoDAO()
        daoprod.insert(produto)

        return "OK"
    }
}