package br.com.biancabessa.ac02.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.biancabessa.ac02.R
import br.com.biancabessa.ac02.`object`.ProdutoPrefs
import br.com.biancabessa.ac02.`object`.ProdutoService
import br.com.biancabessa.ac02.adapter.ProdutosAdapterRV
import br.com.biancabessa.ac02.model.ProdutoClasse
import kotlinx.android.synthetic.main.activity_tela_add_prod.*

class TelaAddProdActivity : DebugActivity() {

    lateinit var btnAddProduto: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_add_prod)

        supportActionBar?.title = "Adionar Produto"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        carregarElementos()

        botao_add.setOnClickListener {
            val editTextProd = editText_prod.text.toString()

            val p = ProdutoClasse()
            p.nome = editTextProd

            Thread{
                if (p.nome != "") {
                    ProdutoService.saveProdutoDB(p)
                }

                runOnUiThread {
                    finish()
                }
            }.start()
        }
    }

    fun carregarElementos(){
        btnAddProduto = findViewById(R.id.botao_add)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}