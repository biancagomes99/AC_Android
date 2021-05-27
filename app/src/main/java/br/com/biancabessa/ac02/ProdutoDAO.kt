package br.com.biancabessa.ac02

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.biancabessa.ac02.model.ProdutoClasse

@Dao
interface ProdutoDAO {

    @Query("SELECT * FROM produto where id = :id")
    fun getById(id: Long): ProdutoClasse?

    @Query("SELECT * FROM produto")
    fun findAll(): List<ProdutoClasse>

    @Insert
    fun insert(produto: ProdutoClasse)

    @Delete
    fun delete(produto: ProdutoClasse)

}