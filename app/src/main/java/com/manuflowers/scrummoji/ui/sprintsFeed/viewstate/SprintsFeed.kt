package com.manuflowers.scrummoji.ui.sprintsFeed.viewstate

import com.manuflowers.scrummoji.data.model.SprintStoriesResponse

sealed class SprintsFeed

class SprintsFeedSuccess(val data: SprintStoriesResponse) : SprintsFeed()
class SprintsFeedError(val error: String) : SprintsFeed()