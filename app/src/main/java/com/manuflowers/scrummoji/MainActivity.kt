package com.manuflowers.scrummoji

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.manuflowers.scrummoji.data.network.buildApiService
import com.manuflowers.scrummoji.repository.JiraRepositoryImpl
import com.manuflowers.scrummoji.ui.login.JiraLoginActivity
import com.manuflowers.scrummoji.ui.login.RoomLoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {



    val database = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createSession()
        setupListeners()
    }

    private fun setupListeners() {
        scrumMasterButton.setOnClickListener {
            startJiraLoginActivity(this)
        }

        developerButton.setOnClickListener {
            startRoomLoginActivity(this)
        }
    }

    fun createSession() {
        val newSession = hashMapOf("sessionName" to "segunda sesion")
        database.collection("sessions")
            .add(newSession)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }

    }

    //para la pantalla del developer mostrar una pantalla cargando y pasarla cuando se suban todas las queries
}

fun startJiraLoginActivity(from: Context) = from.startActivity(Intent(from, JiraLoginActivity::class.java))
fun startRoomLoginActivity(from: Context) = from.startActivity(Intent(from, RoomLoginActivity::class.java))