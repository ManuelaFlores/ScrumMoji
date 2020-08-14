package com.manuflowers.scrummoji.ui.userStoriesFeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.SprintStoriesResponse
import com.manuflowers.scrummoji.repository.FirebaseRepository
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedActivity.Companion.USER_STORIES_DATA
import kotlinx.android.synthetic.main.activity_user_stories_feed.*
import org.koin.android.ext.android.inject

class UserStoriesFeedActivity : AppCompatActivity() {

    private val sprintsList by lazy {
        intent.getParcelableExtra<SprintStoriesResponse>(USER_STORIES_DATA)
    }

    private val fireBaseDatabase : FirebaseRepository by inject()

    private val adapter by lazy {
        UserStoriesAdapter {
            fireBaseDatabase.sendStoryToDatabase(it){
                //TODO: create path for devs and start listen changes on this new path
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_stories_feed)
        setupAdapter()
        setupListeners()
        fireBaseDatabase.addRealTimeListener()
    }

    private fun setupAdapter() {
        userStoriesFeedRecyclerView.adapter = adapter
        sprintsList?.let { sprintsResponse ->
            adapter.addData(sprintsResponse.issues)
        }
    }

    private fun setupListeners() {
        //TODO : Crear referencia a firebase "sessions"
        createSessionButton.setOnClickListener {
            fireBaseDatabase.generateSessionPath()
            fireBaseDatabase.registerNewSession {
                Log.e("SESION", it)
            }

        }
    }
}