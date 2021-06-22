package com.example.mebelorayi.Fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mebelorayi.R
import com.example.mebelorayi.ui.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment: Fragment(R.layout.profile_fragment){
    private val mAuth = FirebaseAuth.getInstance()
    private val fStore = FirebaseFirestore.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnQuitAccount.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

    }

    override fun onStart() {
        super.onStart()
        val docRef = fStore.collection("First Collection").document("User data")
        progressBar.visibility = View.VISIBLE
        docRef.get()
            .addOnSuccessListener {
                progressBar.visibility = View.GONE
                titleQuit.text = "Вы действительно хотите выйти?"
                ivProfilePhoto.visibility = View.VISIBLE
                if (it!=null){
                    Log.d(TAG, "DocumentSnapshot data : ${it.data}")
                    it.getString("Name")?.let { it ->
                        tvUserName.text = it
                    }
                    it.getString("Surname")?.let { it ->
                        tvUserSurname.text = it
                    }
                }else{
                    Log.d(TAG, "No such document")
                }

            }
            .addOnFailureListener {
                progressBar.visibility = View.GONE
                Log.d(TAG, "get failed with", it)
            }

    }

}