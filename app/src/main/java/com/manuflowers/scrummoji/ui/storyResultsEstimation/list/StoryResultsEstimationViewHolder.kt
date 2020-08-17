package com.manuflowers.scrummoji.ui.storyResultsEstimation.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.scrummoji.data.model.StoryPointEstimation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.story_result_estimation_view_holder.view.*

class StoryResultsEstimationViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(storyPointEstimation: StoryPointEstimation) = with(containerView) {
        userNickNameTextView.text = storyPointEstimation.userNickname
        userPointsEstimatedTextView.text = storyPointEstimation.storyPoints.toString()
    }
}