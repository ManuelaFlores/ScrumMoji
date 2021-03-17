package com.manuflowers.domain.sprints.models

data class SprintsModel(
    val maxResults: Int,
    val startAt: String,
    val isLast: Boolean,
    val values: List<SprintModel>
)

data class SprintModel(
    val id: Int,
    val self: String,
    val state: String,
    val name: String,
    val originBoardId: Int
)