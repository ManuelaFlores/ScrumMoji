package com.manuflowers.scrummoji.ui.storyResultsEstimation.viewstate

import com.manuflowers.scrummoji.repository.StoryPointEstimation

sealed class ResultsEstimationState
class SuccessResultEstimationListResponse(
    val data: List<StoryPointEstimation>
) : ResultsEstimationState()
class SuccessResultEstimationResponse(
    val data: StoryPointEstimation
) : ResultsEstimationState()
class ResultEstimationError(val error: String) : ResultsEstimationState()