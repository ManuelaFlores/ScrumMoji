package com.manuflowers.scrummoji.ui.userStoriesFeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.SprintStoriesResponse
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedActivity.Companion.USER_STORIES_DATA
import kotlinx.android.synthetic.main.activity_sprints_feed.*
import kotlinx.android.synthetic.main.activity_user_stories_feed.*

class UserStoriesFeedActivity : AppCompatActivity() {

    private val sprintsList by lazy {
        intent.getParcelableExtra<SprintStoriesResponse>(USER_STORIES_DATA)
    }

    private val adapter by lazy {
        UserStoriesAdapter{
            //TODO: Pasar todo el modelo de la historia
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_stories_feed)
        setupAdapter()
    }

    private fun setupAdapter() {
        userStoriesFeedRecyclerView.adapter = adapter
        sprintsList?.let { sprintsResponse ->
            adapter.addData(sprintsResponse.issues)
        }
    }
}