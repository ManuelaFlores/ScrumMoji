package com.manuflowers.scrummoji

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manuflowers.scrummoji.ui.login.JiraLoginActivity
import com.manuflowers.scrummoji.ui.login.RoomLoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    //para la pantalla del developer mostrar una pantalla cargando y pasarla cuando se suban todas las queries
}

fun startJiraLoginActivity(from: Context) = from.startActivity(Intent(from, JiraLoginActivity::class.java))
fun startRoomLoginActivity(from: Context) = from.startActivity(Intent(from, RoomLoginActivity::class.java))