package com.manuflowers.scrummoji.ui.sprintsFeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.GetSprintsResponse
import com.manuflowers.scrummoji.ui.login.JiraLoginActivity.Companion.LOGIN_DATA
import kotlinx.android.synthetic.main.activity_sprints_feed.*

class SprintsFeedActivity : AppCompatActivity() {

    private val sprintsList by lazy {
        intent.getParcelableExtra<GetSprintsResponse>(LOGIN_DATA)
    }

    private val adapter by lazy {
        SprintsFeedAdapter {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sprints_feed)
        setupAdapter()
    }

    private fun setupAdapter() {
        sprintsFeedRecyclerView.adapter = adapter
        sprintsList?.let { sprintsResponse ->
            adapter.addData(sprintsResponse.values)
        }

    }
}