package br.com.biancabessa.ac02.`object`

import android.content.SharedPreferences
import br.com.biancabessa.ac02.model.LMSApplication

object ProdutoPrefs {

    private fun produtoPrefs():SharedPreferences{
        val context = LMSApplication.getInstance().applicationContext
        return context.getSharedPreferences("LMS", 0)
    }

    fun setBoolean(flag: String, valor: Boolean){
        produtoPrefs().edit().putBoolean(flag, valor).apply()
    }

    fun getBoolean(flag: String): Boolean{
        return produtoPrefs().getBoolean(flag, false)
    }

    fun setString(flag: String, valor: String){
        produtoPrefs().edit().putString(flag, valor).apply()
    }

    fun getString(flag: String): String?{
        return produtoPrefs().getString(flag, "")
    }
}