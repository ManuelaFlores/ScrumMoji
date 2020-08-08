package com.manuflowers.scrummoji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val jiraRepositoryImpl = JiraRepositoryImpl(buildApiService())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prueba()
    }

    fun prueba() {
        GlobalScope.launch {
            val response = jiraRepositoryImpl.getSprints()
            Log.e("RESPONSE", "${response.values}")
        }
    }
}