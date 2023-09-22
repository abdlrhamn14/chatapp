package com.route.chatapplication1.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.route.chatapplication1.databinding.ActivitySplashBinding
import com.route.chatapplication1.login.Login

import com.route.chatapplication1.register.Register

class Splash : AppCompatActivity() {
    lateinit var viewBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        startLoginActivity()
    }

    private fun startLoginActivity() {
        Handler(Looper.getMainLooper())
            .postDelayed({
                var intent= Intent(this@Splash,Login::class.java)
                startActivity(intent)
            },2000)
    }
}