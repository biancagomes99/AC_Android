package br.com.biancabessa.ac02

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_add_prod.*

class TelaAddProdActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_add_prod)

        supportActionBar?.title = "Adionar Produto"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        botao_add.setOnClickListener{
            val nomeprod = prod.text.toString()
            val params = Bundle()
            params.putString("nome", nomeprod)
            Toast.makeText(this, "$nomeprod", Toast.LENGTH_SHORT).show()
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