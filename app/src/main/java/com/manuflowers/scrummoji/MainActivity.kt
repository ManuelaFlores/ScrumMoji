package com.manuflowers.scrummoji

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.manuflowers.data.repository.sprints.model.UserCredentialData
import com.manuflowers.data.repository.sprints.model.toDomain
import com.manuflowers.domain.sprints.GetSprintsUseCase
import com.manuflowers.domain.sprints.SprintsRepository
import com.manuflowers.scrummoji.ui.jiraLogin.JiraLoginActivity
import com.manuflowers.scrummoji.ui.roomLogin.RoomLoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val useCase: GetSprintsUseCase by inject()

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

        lifecycleScope.launch {
            Log.e(
                "EEEEEEEEE",
                "${
                    useCase.getSprints(
                        UserCredentialData(
                            "fmanuela499@gmail.com",
                            "EvSdiVb8c5V8o8QkFuJDEF5E"
                        ).toDomain()
                    )
                }"
            )
        }
    }

    //para la pantalla del developer mostrar una pantalla cargando y pasarla cuando se suban todas las queries
}

fun startJiraLoginActivity(from: Context) =
    from.startActivity(Intent(from, JiraLoginActivity::class.java))

fun startRoomLoginActivity(from: Context) =
    from.startActivity(Intent(from, RoomLoginActivity::class.java))