package br.com.biancabessa.ac02.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "cadastro")
class AddCadastro: Serializable {

    @PrimaryKey
    var id: Long? = null
    var usuario = ""
    var senha = ""

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}