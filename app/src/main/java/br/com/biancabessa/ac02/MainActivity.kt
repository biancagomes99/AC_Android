package br.com.biancabessa.ac02

//import android.content.Intent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.biancabessa.ac02.AddCadastroService.getAddCadastro
import kotlinx.android.synthetic.main.activity_cadastros.*
import kotlinx.android.synthetic.main.login.*


class MainActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        imagebessa.setImageResource(R.drawable.logobessa)

        campo_usuario.setText(Prefs.getString("nome_usuario"))
        campo_senha.setText(Prefs.getString("senha"))
        checkbox_login.isChecked = Prefs.getBoolean("checkLogin")

        recycle_cadastro?.layoutManager = LinearLayoutManager(this)

        val context: Context = this

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
            Toast.makeText(this, "Clocou no botão de login", Toast.LENGTH_SHORT).show()
        }

        //usuário = "aluno"
        //senha = impacta
        //var usuario: String = "aluno"
        //var senha: String = "impacta"
        //botao_login.setOnClickListener {
            //if (campo_usuario.getText().toString().trim().equals(usuario) && campo_senha.getText().toString().trim().equals(senha)) {
            //    Toast.makeText(this, "Acesso liberado", Toast.LENGTH_SHORT).show()

            //    val intent = Intent(this, TelaInicialActivity::class.java)
            //    startActivity(intent)
            //}
            //else{
            //    Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
            //}
        //}

        botao_novoUsuario.setOnClickListener {
                val intent = Intent(this, CadastroActivity::class.java)
                startActivity(intent)
            }

        botao_login.setOnClickListener {
            val intent = Intent(this, TelaInicialActivity::class.java)
            startActivity(intent)
        }
    }

    /*var cadastro = listOf<AddCadastro>()
    override fun onResume() {
        super.onResume()
        Thread {
            AddCadastroService.getCadastroDB()

            runOnUiThread {
                if (campo_usuario in cadastro && campo_senha in cadastro) {
                    Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, TelaInicialActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }*/

    /*fun onClickCadastro(cadastro: AddCadastro){
        val intent = Intent(this, TelaInicialActivity::class.java)
        intent.putExtra("cadastro", cadastro)
        startActivity(intent)
    }*/
}