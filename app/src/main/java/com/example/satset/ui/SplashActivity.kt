package com.example.satset.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.satset.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(b.root)

        if (session.isLoggedIn()) {
            b.tvWelcome.text = "Selamat datang kembali, ${session.getUsername()}!"
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val next = if (session.isLoggedIn()) MainActivity::class.java else LoginActivity::class.java
            startActivity(Intent(this, next))
            finish()
        }, 2000)
    }
}