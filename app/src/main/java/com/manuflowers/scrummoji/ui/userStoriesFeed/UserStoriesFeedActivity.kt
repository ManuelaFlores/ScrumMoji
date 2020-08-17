package com.manuflowers.scrummoji.ui.userStoriesFeed

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.SprintStoriesResponse
import com.manuflowers.scrummoji.data.model.UserStory
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedActivity.Companion.CURRENT_SPRINT_ID
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedActivity.Companion.USER_STORIES_DATA
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedViewModel
import com.manuflowers.scrummoji.ui.sprintsFeed.viewstate.SprintsFeedError
import com.manuflowers.scrummoji.ui.sprintsFeed.viewstate.SprintsFeedSuccess
import com.manuflowers.scrummoji.ui.storyPointsResultsSM.StoryPointsResultsActivity
import com.manuflowers.scrummoji.ui.userStoriesFeed.list.UserStoriesAdapter
import com.manuflowers.scrummoji.utils.toast
import kotlinx.android.synthetic.main.activity_user_stories_feed.*
import org.koin.android.ext.android.inject

class UserStoriesFeedActivity : AppCompatActivity() {

    private val currentSprintId by lazy {
        intent.getIntExtra(CURRENT_SPRINT_ID, 0)
    }

    private val viewModel: UserStoriesFeedViewModel by inject()
    private val sprintsFeedViewModel: SprintsFeedViewModel by inject()

    private val adapter by lazy { UserStoriesAdapter(::sendStoryToDatabase) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_stories_feed)
        setupAdapter()
        setupListeners()
        setSupportActionBar(userStoriesFeedToolbar)
        sprintsFeedViewModel.getSprintStories(currentSprintId)
        setupObservers()
    }

    private fun setupObservers() {
        sprintsFeedViewModel.sprintsLiveData.observe(this, Observer { sprintsFeed ->
            when (sprintsFeed) {
                is SprintsFeedSuccess -> {
                    adapter.addData(sprintsFeed.data.issues)
                }
                is SprintsFeedError -> {
                    this.toast(sprintsFeed.error)
                }
            }
        })
    }

    private fun setupAdapter() {
        userStoriesFeedRecyclerView.adapter = adapter
    }

    private fun sendStoryToDatabase(userStory: UserStory) {
        viewModel.sendStoryToDatabase(userStory) {
            val intent = Intent(this, StoryPointsResultsActivity::class.java)
            intent.putExtra(USER_STORY_DATA, userStory)
            intent.putExtra(CURRENT_SPRINT_ID, sprintsFeedViewModel.getCurrentSprintId())
            startActivity(intent)
        }
    }

    private fun setupListeners() {
        createSessionButton.setOnClickListener { view ->
            view.isEnabled = false
            view.isVisible = false
            viewModel.generateSessionPath { sessionId ->
                idSessionTextView.isVisible = true
                idSessionTextView.text = getString(R.string.session_id_v1, sessionId)
                copyButton.isVisible = true
            }
        }

        copyButton.setOnClickListener { copySessionId() }
    }

    private fun copySessionId() {
        val myClipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?

        val roomId = idSessionTextView.text.toString().substringAfter(':')
        viewModel.saveRoomId(roomId.trim())

        val myClip = ClipData.newPlainText(SESSION_ID, roomId.trim())
        myClipboard?.setPrimaryClip(myClip)
        this.toast(getString(R.string.session_id_copied))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val SESSION_ID = "SESSION_ID"
        const val USER_STORY_DATA = "USER_STORY_DATA"
    }
}