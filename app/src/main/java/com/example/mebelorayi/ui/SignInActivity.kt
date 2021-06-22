package com.example.mebelorayi.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mebelorayi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()

        btnOK.setOnClickListener {
            if (etLogin.text.isNotEmpty() && etPassword.text.isNotEmpty()) {
                loadingProgressBar.visibility = View.VISIBLE
                mAuth.signInWithEmailAndPassword(
                    etLogin.text.toString(),
                    etPassword.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val currentUser = mAuth.currentUser
                            loadingProgressBar.visibility = View.GONE
                            updateUI(currentUser)
                        } else {
                            loadingProgressBar.visibility = View.GONE
                            Toast.makeText(
                                this,
                                "Неправильно ввели логин или пароль",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
            } else {
                loadingProgressBar.visibility = View.GONE
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
            }

        }

        ivRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}