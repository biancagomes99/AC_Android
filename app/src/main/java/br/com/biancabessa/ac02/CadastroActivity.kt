package br.com.biancabessa.ac02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_cadastros.*

class CadastroActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastros)
        supportActionBar?.title = "Novo Usuário"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        eventoClick()

        //var cadastro = listOf<AddCadastro>()

        recycle_cadastro?.layoutManager = LinearLayoutManager(this)
    }

    var cadastro = listOf<AddCadastro>()
    override fun onResume(){
        super.onResume()
        Thread {
            cadastro = AddCadastroService.getCadastroDB()

            runOnUiThread {
                recycle_cadastro?.adapter = AddCadastroAdapter(cadastro) {
                    onClickCadastro(it)
                }
            }
        }.start()
    }

    fun onClickCadastro(cadastro: AddCadastro){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("cadastro", cadastro)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    fun eventoClick() {
        btn_cadastro.setOnClickListener {
                //Toast.makeText(this, "checando", Toast.LENGTH_LONG).show()
            val textoUsuario = editText_usuario.text.toString()
            val textoSenha = editText_senha.text.toString()

            val d = AddCadastro()
            d.usuario = textoUsuario
            d.senha = textoSenha

                //.makeText(this, "$textoUsuario", Toast.LENGTH_LONG).show()
                //Toast.makeText(this, "$textoSenha", Toast.LENGTH_LONG).show()
                //println("Teste " + d.toJson())
                //Log.i("teste", "Teste" + d.toJson())
            Thread {
                AddCadastroService.saveCadastroDB(d)

                runOnUiThread {
                    var intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("Produtos", this.cadastro.get(0))

                    NotificationUtil.create(1, intent, "MS-Orfeu", "Cadastro concluido com sucesso")

                    finish()
                }
            }.start()
        }
    }
}