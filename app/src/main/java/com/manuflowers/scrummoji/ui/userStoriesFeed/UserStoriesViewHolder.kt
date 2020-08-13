package com.manuflowers.scrummoji.ui.userStoriesFeed

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.scrummoji.data.model.IssueResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.sprints_feed_view_holder.view.*

class UserStoriesViewHolder(
    override val containerView: View
): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(issueResponse: IssueResponse, onUserStorySelected: (storyId: String) -> Unit) = with(containerView) {
        sprintTextView.text = issueResponse.fields.storyTitle
        sprintTextView.setOnClickListener {
            onUserStorySelected.invoke(issueResponse.id)
        }
    }

}