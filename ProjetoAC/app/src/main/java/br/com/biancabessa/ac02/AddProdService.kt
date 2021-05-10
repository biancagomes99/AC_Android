package br.com.biancabessa.ac02

object AddProdService {
    fun getAddProd(): List<AddProd>{
        var produtos = mutableListOf<AddProd>()

        for (i in 1..10){
            val p = AddProd()
            p.nome = "$i Produto"
            produtos.add(p)
        }
        return produtos
    }
}