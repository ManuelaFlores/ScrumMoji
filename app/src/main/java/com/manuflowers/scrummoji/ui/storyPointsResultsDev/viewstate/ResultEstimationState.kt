package com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate

import com.manuflowers.scrummoji.data.model.StoryPointEstimation

sealed class ResultsEstimationState
class SuccessResultEstimationListResponse(
    val data: List<StoryPointEstimation>
) : ResultsEstimationState()
class SuccessResultEstimationResponse(
    val data: StoryPointEstimation
) : ResultsEstimationState()
class ResultEstimationError(val error: String) : ResultsEstimationState()