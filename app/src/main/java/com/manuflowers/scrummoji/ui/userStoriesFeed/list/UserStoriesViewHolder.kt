package com.manuflowers.scrummoji.ui.userStoriesFeed.list

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.scrummoji.data.model.IssueResponse
import com.manuflowers.scrummoji.data.model.UserStory
import com.manuflowers.scrummoji.data.model.asUserStoryModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.sprints_feed_view_holder.view.*

class UserStoriesViewHolder(
    override val containerView: View
): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(issueResponse: IssueResponse, onUserStorySelected: (userStory: UserStory) -> Unit) = with(containerView) {
        sprintTextView.text = issueResponse.fields.storyTitle
        if (issueResponse.fields.storyPointEstimate != null && issueResponse.fields.storyPointEstimate != 0.0) {
            totalPoints.isVisible = true
            totalPoints.text = issueResponse.fields.storyPointEstimate.toInt().toString()
        } else {
            totalPoints.isVisible = false
        }
        sprintTextView.setOnClickListener {
            onUserStorySelected.invoke(issueResponse.asUserStoryModel())
        }
    }

}