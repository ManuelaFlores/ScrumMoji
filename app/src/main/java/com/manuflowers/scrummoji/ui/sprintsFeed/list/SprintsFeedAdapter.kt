package com.manuflowers.scrummoji.ui.sprintsFeed.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.SprintModelResponse
import com.manuflowers.scrummoji.ui.sprintsFeed.list.SprintsFeedViewHolder

class SprintsFeedAdapter(
    private val onSprintSelected: (sprintId: Int) -> Unit
) : RecyclerView.Adapter<SprintsFeedViewHolder>() {

    private val sprintsList = mutableListOf<SprintModelResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SprintsFeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sprints_feed_view_holder, parent, false)
        return SprintsFeedViewHolder(
            view
        )
    }

    override fun getItemCount() = sprintsList.size

    override fun onBindViewHolder(holder: SprintsFeedViewHolder, position: Int) {
        holder.bind(sprintsList[position], onSprintSelected)
    }

    fun addData(sprintsList: List<SprintModelResponse>) {
        this.sprintsList.addAll(sprintsList)
    }
}