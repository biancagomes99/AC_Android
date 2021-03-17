package br.com.biancabessa.ac02

//import android.content.Intent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        imagebessa.setImageResource(R.drawable.logobessa)

        //usuário = "aluno"
        //senha = impacta
        var usuario: String = "aluno"
        var senha: String = "impacta"
        botao_login.setOnClickListener {
            if (campo_usuario.getText().toString().trim().equals(usuario) && campo_senha.getText().toString().trim().equals(senha)) {
                Toast.makeText(this, "Acesso liberado", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, TelaInicialActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}