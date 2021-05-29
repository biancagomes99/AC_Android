package br.com.biancabessa.ac02.`object`

import br.com.biancabessa.ac02.model.ProdutoClasse

object ProdutoService {

    fun getProdutosDB(): List<ProdutoClasse>{
        val daoprod = DatabaseManager.getProdutoDAO()
        return daoprod.findAll()
    }

    fun saveProdutoDB(produto: ProdutoClasse): String{
        val daoprod = DatabaseManager.getProdutoDAO()
        daoprod.insert(produto)

        return "OK"
    }

    fun deleteProdutoDB(produto: ProdutoClasse){
        val daoprod = DatabaseManager.getProdutoDAO()
        daoprod.delete(produto)
    }
}