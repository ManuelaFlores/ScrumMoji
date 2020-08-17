package com.manuflowers.scrummoji.ui.storyPointsResultsSM

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.UserStory
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedActivity
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.list.StoryResultsEstimationAdapter
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.*
import com.manuflowers.scrummoji.ui.userStoriesFeed.UserStoriesFeedActivity
import com.manuflowers.scrummoji.ui.userStoriesFeed.UserStoriesFeedActivity.Companion.USER_STORY_DATA
import com.manuflowers.scrummoji.utils.toast
import kotlinx.android.synthetic.main.activity_story_points_results.*
import org.koin.android.ext.android.inject

class StoryPointsResultsActivity : AppCompatActivity() {

    private val resultsViewModel: StoryPointsResultSMViewModel by inject()

    private val userStory by lazy {
        intent.getParcelableExtra<UserStory>(USER_STORY_DATA)
    }

    private val currentSprintId by lazy {
        intent.getIntExtra(SprintsFeedActivity.CURRENT_SPRINT_ID, 0)
    }

    private val adapter by lazy {
        StoryResultsEstimationAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_points_results)
        setSupportActionBar(estimatedResultsToolbar)
        setupViews()
        setupListeners()
        setupList()
        subscribeData()
        listenNewEstimationUploaded()
    }

    private fun listenNewEstimationUploaded() {
        userStory?.let {
            resultsViewModel.listenNewEstimationUploaded(it.id)
        }
    }

    private fun subscribeData() {
        resultsViewModel.resultsEstimationStateLiveData.observe(this, Observer {
            onResultsEstimationStateChanged(it)
        })
    }

    private fun onResultsEstimationStateChanged(resultsEstimationState: ResultsEstimationState) {
        when (resultsEstimationState) {
            is SuccessResultEstimationListResponse -> {
                adapter.setResults(resultsEstimationState.data)
                getHighestEstimation()
            }
            is SuccessResultEstimationResponse -> {
                adapter.addNewResult(resultsEstimationState.data)
                getHighestEstimation()
            }

            is UpdateUSerStoryStateSuccess -> {
                val intent = Intent(this, UserStoriesFeedActivity::class.java)
                //intent.putExtra(SprintsFeedActivity.USER_STORIES_DATA, sprintsList)
                intent.putExtra(SprintsFeedActivity.CURRENT_SPRINT_ID, currentSprintId)
                startActivity(intent)
            }
            is ResultEstimationError -> {
                this.toast(resultsEstimationState.error)
            }
        }
    }

    private fun setupList() {
        storyPointsResultRecyclerView.adapter = adapter
    }

    private fun setupViews() {
        userStory?.let {
            storyTitleTextView.text = it.title
        }
    }

    private fun getHighestEstimation() {
        val result = adapter.calculateHighestEstimation() ?: return
        resultTextView.text = "Estimated points for this story: ${result.storyPoints}"
    }

    private fun setupListeners() {
        assignPointsButton.setOnClickListener {
            userStory?.let {
                resultsViewModel.updateStory(
                    pointsSelected = adapter.getHighestEstimation()?.storyPoints.toString(),
                    storyId = it.id
                )
            }
        }

        assignItMyselfButton.setOnClickListener {
            assignYourPointsContainer.isVisible = true
            assignPointsContainer.isVisible = false
        }
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
}