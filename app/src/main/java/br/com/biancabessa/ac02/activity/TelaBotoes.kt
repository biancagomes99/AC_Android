package br.com.biancabessa.ac02.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.biancabessa.ac02.R

class TelaBotoes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_botoes)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var params = intent.extras
        var flag = params?.getInt("flag")
        when(flag){
            1 -> supportActionBar?.title = "Tela 01"
            2 -> supportActionBar?.title = "Tela 02"
            3 -> supportActionBar?.title = "Tela 03"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}