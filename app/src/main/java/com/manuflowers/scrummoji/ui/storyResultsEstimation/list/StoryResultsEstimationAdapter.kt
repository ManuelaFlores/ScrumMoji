package com.manuflowers.scrummoji.ui.storyResultsEstimation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.repository.StoryPointEstimation

class StoryResultsEstimationAdapter : RecyclerView.Adapter<StoryResultsEstimationViewHolder>() {

    private val storyResultsEstimationList = mutableListOf<StoryPointEstimation>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoryResultsEstimationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.story_result_estimation_view_holder, parent, false)
        return StoryResultsEstimationViewHolder(
            view
        )
    }

    override fun getItemCount() = storyResultsEstimationList.size

    override fun onBindViewHolder(holder: StoryResultsEstimationViewHolder, position: Int) {
        holder.bind(storyResultsEstimationList[position])
    }

    private fun addNewResult(storyPointEstimation: StoryPointEstimation) {
        this.storyResultsEstimationList.add(storyPointEstimation)
        notifyDataSetChanged()
    }
}