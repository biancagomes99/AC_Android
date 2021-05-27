package br.com.biancabessa.ac02.`object`

import android.util.Log
import br.com.biancabessa.ac02.model.AddCadastro
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AddCadastroService {

    val host = "https://biancabessa.pythonanywhere.com"
    val TAG = "WS_Orfeu"

    fun getAddCadastro(): List<AddCadastro>{

        val url = "$host/cadastros"
        val json = HttpHelper.get(url)

        Log.d(TAG, json)

        var cadastro = parserJson<List<AddCadastro>>(json)

        return cadastro
    }

    fun getCadastroDB(): List<AddCadastro>{
        val dao = DatabaseManager.getCadastroDAO()
        return dao.findAll()
    }

    fun saveCadastro(cadastro: AddCadastro): String{
        val json = HttpHelper.post("$host/cadastro", cadastro.toJson())
        return json
    }

    fun saveCadastroDB(cadastro: AddCadastro): String{
        val dao = DatabaseManager.getCadastroDAO()
        dao.insert(cadastro)

        return "OK"
    }

    inline fun <reified T> parserJson(json: String) : T{
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}