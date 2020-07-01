package com.example.datanermobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.appdynamics.eumagent.runtime.AgentConfiguration
import com.appdynamics.eumagent.runtime.Instrumentation

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Instrumentation.start(AgentConfiguration.builder()
//            .withAppKey("AD-AAB-AAX-JEN")
//            .withContext(applicationContext)
//            .withCollectorURL("https://pdx-col.eum-appdynamics.com/")
//            .build())

        Instrumentation.start("AD-AAB-AAX-JEN", applicationContext)

        setContentView(R.layout.activity_splash)
        changeToLogin()
    }
    fun changeToLogin(){
        val intent = Intent(this, LoginActivity::class.java)

        Handler().postDelayed({
            intent.change()
        }, 2000)
    }
    fun Intent.change(){
        startActivity(this)
        finish()
    }
}
