package org.telegram.telegrammanager.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_authorisation_password.*
import org.drinkless.td.libcore.telegram.apihelper.AuthorizationManager
import org.telegram.telegrammanager.Helpers.TGClient.tClient
import org.telegram.telegrammanager.MainActivity
import org.telegram.telegrammanager.R

class PasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorisation_password)
        passwordButton.setOnClickListener {
            tClient.authManager.sendPassword(passwordField.text.toString())
        }
        tClient.updatesHandler = AfterPassHandler()
    }
    inner class AfterPassHandler : org.drinkless.td.libcore.telegram.apihelper.Handler{
        override fun handle(type: String?, obj: Any?) {
            if (type.equals("authState")){
                val state = obj as Int
                if (state == AuthorizationManager.READY){
                    val intent = Intent(this@PasswordActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            } else if (type.equals("ERROR")){
                Log.e("Authentication", "Error occured")
            }
        }
    }
}