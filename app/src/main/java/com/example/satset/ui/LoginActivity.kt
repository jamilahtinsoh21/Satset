package com.example.satset.ui

import android.content.Intent
import android.os.Bundle
import com.example.satset.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private lateinit var b: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnLogin.setOnClickListener {
            val user = b.etUsername.text.toString().trim()
            val pass = b.etPassword.text.toString()

            b.tilUsername.error = if (user.length < 3) "Minimal 3 karakter" else null
            b.tilPassword.error = if (pass.length < 6) "Minimal 6 karakter" else null
            if (b.tilUsername.error != null || b.tilPassword.error != null) return@setOnClickListener

            session.login(user)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}