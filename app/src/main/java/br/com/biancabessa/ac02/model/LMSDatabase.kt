package br.com.biancabessa.ac02.model

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.biancabessa.ac02.CadastroDAO
import br.com.biancabessa.ac02.ProdutoDAO

@Database(entities = arrayOf(AddCadastro::class, ProdutoClasse::class), version = 1)
abstract class LMSDatabase: RoomDatabase() {
    abstract fun cadastroDAO(): CadastroDAO

    abstract fun produtoDAO(): ProdutoDAO
}