package com.visma.technical.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.visma.technical.DashboardActivity
import com.visma.technical.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this


        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()


        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        binding.signInButton.setOnClickListener {

            val intent = mGoogleSignInClient.signInIntent

            startActivityForResult(intent,1)

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if(requestCode == 1) {

            Toast.makeText(this,"Login correcto", Toast.LENGTH_LONG).show()

            val intent = Intent(this, DashboardActivity::class.java)

            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)


    }


    private fun signIn() {


    }
}