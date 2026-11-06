package com.example.satset.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.satset.data.SessionManager
import com.example.satset.databinding.ViewUserBarBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = SessionManager(this)
    }

    protected fun bindUserBar(bar: ViewUserBarBinding) {
        val name = session.getUsername()
        bar.tvInitial.text = name.take(1).uppercase()
        bar.tvGreeting.text = name
        bar.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Keluar")
                .setMessage("Yakin ingin keluar dari akun $name?")
                .setNegativeButton("Batal", null)
                .setPositiveButton("Keluar") { _, _ ->
                    session.logout()
                    startActivity(
                        Intent(this, LoginActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    )
                }.show()
        }
    }
}