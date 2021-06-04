package br.com.biancabessa.ac02.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import br.com.biancabessa.ac02.R
import br.com.biancabessa.ac02.`object`.AddCadastroService
import br.com.biancabessa.ac02.`object`.Prefs
import br.com.biancabessa.ac02.model.AddCadastro
import kotlinx.android.synthetic.main.login.*


class MainActivity : DebugActivity() {

    var cadastro = listOf<AddCadastro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        imagebessa.setImageResource(R.drawable.logobessa)

        campo_usuario.setText(Prefs.getString("nome_usuario"))
        campo_senha.setText(Prefs.getString("senha"))
        checkbox_login.isChecked = Prefs.getBoolean("checkLogin")

        checkbox_login.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed(object :  Runnable {
                override fun run() {
                    val nome_usuario = campo_usuario.text.toString()
                    val senha = campo_senha.text.toString()
                    val checkLogin = checkbox_login.isChecked

                    if (checkLogin){
                        Prefs.setString("nome_usuario", nome_usuario)
                        Prefs.setString("senha", senha)
                    }

                    else{
                        Prefs.setString("nome_usuario", "")
                        Prefs.setString("senha", "")
                    }

                    Prefs.setBoolean("checkbox_login", checkLogin)
                }
            }, 3000)
        }

        botao_novoUsuario.setOnClickListener {
                val intent = Intent(this, CadastroActivity::class.java)
                startActivity(intent)
            }

        botao_login.setOnClickListener {
            Thread {
                cadastro = AddCadastroService.getCadastroDB()

            runOnUiThread {
                var existe = cadastro.filter {
                        s -> s.usuario == campo_usuario.text.toString() && s.senha == campo_senha.text.toString()
                }

                if (existe.size != 0) {
                    val intent = Intent(this, TelaInicialActivity::class.java)
                    startActivity(intent)
                }
                else if (existe.size == 0){
                    Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
        }
    }
}