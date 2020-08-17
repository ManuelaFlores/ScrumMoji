package com.manuflowers.scrummoji.ui.storyPointsResultsSM

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.UserStory
import com.manuflowers.scrummoji.ui.userStoriesFeed.UserStoriesFeedActivity.Companion.USER_STORY_DATA
import kotlinx.android.synthetic.main.activity_story_points_results.*

class StoryPointsResultsActivity : AppCompatActivity() {

    private val userStory by lazy {
        intent.getParcelableExtra<UserStory>(USER_STORY_DATA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_points_results)
        setupViews()
    }

    private fun setupViews() {
        userStory?.let {
            storyTitleTextView.text = it.title
        }
    }

    private fun setupListeners() {

    }

    //TODO: Listen for changes
}