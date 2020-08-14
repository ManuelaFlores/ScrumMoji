package com.manuflowers.scrummoji.ui.userStoriesFeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.IssueResponse
import com.manuflowers.scrummoji.repository.UserStory

class UserStoriesAdapter(
    private val onUserStorySelected: (userStory: UserStory) -> Unit
) : RecyclerView.Adapter<UserStoriesViewHolder>(){
    private val userStoriesList = mutableListOf<IssueResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserStoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sprints_feed_view_holder, parent, false)
        return UserStoriesViewHolder(view)
    }

    override fun getItemCount() = userStoriesList.size

    override fun onBindViewHolder(holder: UserStoriesViewHolder, position: Int) {
        holder.bind(userStoriesList[position], onUserStorySelected)
    }

    fun addData(sprintsList: List<IssueResponse>) {
        this.userStoriesList.addAll(sprintsList)
    }
}