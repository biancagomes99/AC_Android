package br.com.biancabessa.ac02.`object`

import androidx.room.Room
import br.com.biancabessa.ac02.CadastroDAO
import br.com.biancabessa.ac02.ProdutoDAO
import br.com.biancabessa.ac02.model.LMSApplication
import br.com.biancabessa.ac02.model.LMSDatabase

object DatabaseManager {

    private var dbInstance: LMSDatabase

    init {
        val context = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            context,
            LMSDatabase::class.java,
            "lms.sqlite"
        ).build()
    }

    fun getCadastroDAO(): CadastroDAO {
        return dbInstance.cadastroDAO()
    }

    fun getProdutoDAO(): ProdutoDAO{
        return dbInstance.produtoDAO()
    }
}