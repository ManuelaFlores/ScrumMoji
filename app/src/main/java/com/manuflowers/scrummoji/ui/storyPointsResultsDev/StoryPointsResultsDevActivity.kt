package com.manuflowers.scrummoji.ui.storyPointsResultsDev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.UserStory
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.list.StoryResultsEstimationAdapter
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.ResultEstimationError
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.ResultsEstimationState
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.SuccessResultEstimationListResponse
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.SuccessResultEstimationResponse
import com.manuflowers.scrummoji.ui.userStoriesFeed.UserStoriesFeedActivity.Companion.USER_STORY_DATA
import com.manuflowers.scrummoji.utils.toast
import kotlinx.android.synthetic.main.activity_story_results_estimation.*
import org.koin.android.ext.android.inject

class StoryPointsResultsDevActivity : AppCompatActivity() {

    private val viewModel: StoryPointsResultsDevViewModel by inject()

    private val userStory by lazy {
        intent.getParcelableExtra<UserStory>(USER_STORY_DATA)
    }

    private val adapter by lazy {
        StoryResultsEstimationAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_results_estimation)
        setupList()
        subscribeData()
        listenNewEstimationListUploaded()
    }

    private fun listenNewEstimationListUploaded() {
        userStory?.let {
            viewModel.listenNewEstimationListUploaded(it.id)
        }
    }

    private fun listenNewEstimationUploaded() {
        userStory?.let {
            viewModel.listenNewEstimationUploaded(it.id)
        }
    }

    private fun subscribeData() {
        viewModel.resultsEstimationStateLiveData.observe(this, Observer {
            onResultsEstimationStateChanged(it)
        })
    }

    private fun onResultsEstimationStateChanged(resultsEstimationState: ResultsEstimationState) {
        when (resultsEstimationState) {
            is SuccessResultEstimationListResponse -> {
                adapter.setResults(resultsEstimationState.data)
                listenNewEstimationUploaded()
            }
            is SuccessResultEstimationResponse -> {
                adapter.addNewResult(resultsEstimationState.data)
            }
            is ResultEstimationError -> {
                this.toast(resultsEstimationState.error)
            }
        }
    }

    private fun setupList() {
        storyResultsEstimationRecyclerView.adapter = adapter
    }
}