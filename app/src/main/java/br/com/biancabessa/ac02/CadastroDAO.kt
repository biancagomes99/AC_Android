package br.com.biancabessa.ac02

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.biancabessa.ac02.model.AddCadastro

@Dao
interface CadastroDAO {

    @Query("SELECT * FROM cadastro where id = :id")
    fun getById(id: Long): AddCadastro?

    @Query("SELECT * FROM cadastro")
    fun findAll(): List<AddCadastro>

    @Insert
    fun insert(cadastro: AddCadastro)

    @Delete
    fun delete(cadastro: AddCadastro)

}