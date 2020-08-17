package com.manuflowers.scrummoji.ui.storyResultsEstimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.ui.storyResultsEstimation.list.StoryResultsEstimationAdapter
import kotlinx.android.synthetic.main.activity_story_results_estimation.*

class StoryResultsEstimationActivity : AppCompatActivity() {

    private val adapter by lazy {
        StoryResultsEstimationAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_results_estimation)
        setupList()
    }

    private fun setupList() {
        storyResultsEstimationRecyclerView.adapter = adapter
    }
}