package br.com.biancabessa.ac02.model

import android.content.Intent
import android.util.Log
import br.com.biancabessa.ac02.`object`.NotificationUtil
import br.com.biancabessa.ac02.`object`.Prefs
import br.com.biancabessa.ac02.activity.TelaAddProdActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebase: FirebaseMessagingService() {

    val TAG = "firebase"
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d(TAG, "Novo token: $token")

        Prefs.setString("FB_TOKEN", token!!)
    }

    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        super.onMessageReceived(mensagemRemota)
        Log.d(TAG, "chegou a mensagem")

        if (mensagemRemota?.notification != null){
            showNotification(mensagemRemota)
        }
    }

    private fun showNotification(mensagemRemota: RemoteMessage?){
        val intent = Intent(this, TelaAddProdActivity::class.java)
        val titulo = mensagemRemota?.notification?.title
        val corpo = mensagemRemota?.notification?.body
        Log.d(TAG, "Titulo: $titulo")
        Log.d(TAG, "Corpo: $corpo")
        NotificationUtil.create(1, intent, titulo!!, corpo!!)
    }

}