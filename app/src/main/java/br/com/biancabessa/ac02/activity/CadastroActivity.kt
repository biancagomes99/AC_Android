package br.com.biancabessa.ac02.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.biancabessa.ac02.R
import br.com.biancabessa.ac02.`object`.AddCadastroService
import br.com.biancabessa.ac02.`object`.NotificationUtil
import br.com.biancabessa.ac02.adapter.AddCadastroAdapter
import br.com.biancabessa.ac02.model.AddCadastro
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
            val textoUsuario = editText_usuario.text.toString()
            val textoSenha = editText_senha.text.toString()

            val d = AddCadastro()
            d.usuario = textoUsuario
            d.senha = textoSenha

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