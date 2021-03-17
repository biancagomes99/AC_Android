package br.com.biancabessa.ac02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        setSupportActionBar(toolbar)

        supportActionBar?.title = "Lista"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_tela01.setOnClickListener{

            val intent = Intent(this, TelaBotoes::class.java)
            Toast.makeText(this, "Tela 01", Toast.LENGTH_SHORT).show()

            val params = Bundle()
            params.putInt("flag", 1)
            intent.putExtras(params)

            startActivity(intent)

        }

        btn_tela02.setOnClickListener{

            val intent = Intent(this, TelaBotoes::class.java)
            Toast.makeText(this, "Tela 02", Toast.LENGTH_SHORT).show()

            val params = Bundle()
            params.putInt("flag", 2)
            intent.putExtras(params)

            startActivity(intent)

        }

        btn_tela03.setOnClickListener{

            val intent = Intent(this, TelaBotoes::class.java)
            Toast.makeText(this, "Tela 03", Toast.LENGTH_SHORT).show()

            val params = Bundle()
            params.putInt("flag", 3)
            intent.putExtras(params)

            startActivity(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id == R.id.action_add){
            Toast.makeText(this, "Clicou adicionar", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, TelaAddProdActivity::class.java)
            startActivity(intent)
        }

        else if(id == R.id.action_buscar){
            Toast.makeText(this, "Clicou pesquisa", Toast.LENGTH_SHORT).show()
        }

        else if (id == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}