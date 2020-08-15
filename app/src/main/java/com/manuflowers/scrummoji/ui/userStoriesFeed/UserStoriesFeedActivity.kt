package com.manuflowers.scrummoji.ui.userStoriesFeed

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.SprintStoriesResponse
import com.manuflowers.scrummoji.repository.FirebaseRepository
import com.manuflowers.scrummoji.repository.UserStory
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedActivity.Companion.USER_STORIES_DATA
import com.manuflowers.scrummoji.ui.userStoriesFeed.list.UserStoriesAdapter
import com.manuflowers.scrummoji.utils.toast
import kotlinx.android.synthetic.main.activity_user_stories_feed.*
import org.koin.android.ext.android.inject

class UserStoriesFeedActivity : AppCompatActivity() {

    private val sprintsList by lazy {
        intent.getParcelableExtra<SprintStoriesResponse>(USER_STORIES_DATA)
    }

    private val fireBaseDatabase: FirebaseRepository by inject()

    private val viewModel: UserStoriesFeedViewModel by inject()

    private val adapter by lazy {
        UserStoriesAdapter(
            ::sendStoryToDatabase
        )
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

    private fun sendStoryToDatabase(userStory: UserStory) {
        viewModel.sendStoryToDatabase(userStory) {
            //TODO: Start new Activity and listen for developers to send their points

        }
    }

    private fun setupListeners() {
        createSessionButton.setOnClickListener { view ->
            view.isEnabled = false
            view.isVisible = false
            viewModel.generateSessionPath { sessionId ->
                Log.e("SESION", sessionId)
                idSessionTextView.isVisible = true
                idSessionTextView.text = getString(R.string.session_id_v1, sessionId)
                copyButton.isVisible = true
            }
        }

        copyButton.setOnClickListener { copySessionId() }
    }

    private fun copySessionId() {
        val myClipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?

        val myClip = ClipData.newPlainText(SESSION_ID, idSessionTextView.text)
        myClipboard?.setPrimaryClip(myClip)
        this.toast(getString(R.string.session_id_copied))
    }

    companion object {
        const val SESSION_ID = "SESSION_ID"
    }
}