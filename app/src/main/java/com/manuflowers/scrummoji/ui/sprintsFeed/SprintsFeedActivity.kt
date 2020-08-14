package com.manuflowers.scrummoji.ui.sprintsFeed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.GetSprintsResponse
import com.manuflowers.scrummoji.ui.login.JiraLoginActivity.Companion.LOGIN_DATA
import com.manuflowers.scrummoji.ui.sprintsFeed.list.SprintsFeedAdapter
import com.manuflowers.scrummoji.ui.sprintsFeed.viewstate.SprintsFeedError
import com.manuflowers.scrummoji.ui.sprintsFeed.viewstate.SprintsFeedSuccess
import com.manuflowers.scrummoji.ui.userStoriesFeed.UserStoriesFeedActivity
import com.manuflowers.scrummoji.utils.toast
import kotlinx.android.synthetic.main.activity_sprints_feed.*
import org.koin.android.ext.android.inject

class SprintsFeedActivity : AppCompatActivity() {

    private val sprintsList by lazy {
        intent.getParcelableExtra<GetSprintsResponse>(LOGIN_DATA)
    }

    private val viewModel: SprintsFeedViewModel by inject()

    private val adapter by lazy {
        SprintsFeedAdapter {
            viewModel.getSprintStories(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sprints_feed)
        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.sprintsLiveData.observe(this, Observer { sprintsFeed ->
            when (sprintsFeed) {
                is SprintsFeedSuccess -> {
                    val intent = Intent(this, UserStoriesFeedActivity::class.java)
                    intent.putExtra(USER_STORIES_DATA, sprintsFeed.data)
                    startActivity(intent)
                }
                is SprintsFeedError -> {
                    this.toast(sprintsFeed.error)
                }
            }
        })
    }

    private fun setupAdapter() {
        sprintsFeedRecyclerView.adapter = adapter
        sprintsList?.let { sprintsResponse ->
            adapter.addData(sprintsResponse.values)
        }

    }

    companion object {
        const val USER_STORIES_DATA = "USER_STORIES_DATA"
    }
}