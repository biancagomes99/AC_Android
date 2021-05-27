package br.com.biancabessa.ac02.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "produto")
class ProdutoClasse: Serializable {

    @PrimaryKey
    var id: Long? = null
    var nome = ""

}