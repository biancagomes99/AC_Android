package br.com.biancabessa.ac02.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.biancabessa.ac02.R
import br.com.biancabessa.ac02.`object`.AddCadastroService
import br.com.biancabessa.ac02.`object`.NotificationUtil
import br.com.biancabessa.ac02.adapter.AddCadastroAdapter
import br.com.biancabessa.ac02.model.AddCadastro
import kotlinx.android.synthetic.main.activity_cadastros.*
import kotlinx.android.synthetic.main.login.*

class CadastroActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastros)
        supportActionBar?.title = "Novo Usuário"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        eventoClick()

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
            //var cadastro = listOf<AddCadastro>()
            val textoUsuario = editText_usuario.text.toString()
            val textoSenha = editText_senha.text.toString()
            val txtCorfirmaSenha = editText_corfirmasenha.text.toString()

            val d = AddCadastro()
            d.usuario = textoUsuario
            d.senha = textoSenha

            Thread {
                AddCadastroService.saveCadastroDB(d)

                runOnUiThread {

                    if (d.usuario != "" && d.senha != "") {
                        if (d.senha == txtCorfirmaSenha) {
                            var intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("Produtos", this.cadastro.get(0))

                            NotificationUtil.create(1, intent, "MS-Orfeu", "Cadastro concluido com sucesso")

                            finish()
                        }
                        Toast.makeText(this, "Confirme a senha", Toast.LENGTH_LONG).show()
                    }

                    else{
                        Toast.makeText(this, "Login inválido", Toast.LENGTH_LONG).show()
                    }

                }
            }.start()
        }
    }
}