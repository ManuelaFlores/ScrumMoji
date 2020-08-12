package com.manuflowers.scrummoji.ui.sprintsFeed

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.scrummoji.data.model.SprintModelResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.sprints_feed_view_holder.view.*

class SprintsFeedViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(sprintModelResponse: SprintModelResponse, onSprintSelected: (sprintId: Int) -> Unit) =
        with(containerView) {
            sprintTextView.text = sprintModelResponse.name
            sprintTextView.setOnClickListener {
                onSprintSelected.invoke(sprintModelResponse.id)
            }
        }
}