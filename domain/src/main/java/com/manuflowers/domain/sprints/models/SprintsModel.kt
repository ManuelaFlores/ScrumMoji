package com.manuflowers.domain.sprints.models

data class SprintsEntity(
    val maxResults: Int,
    val startAt: String,
    val isLast: Boolean,
    val values: List<SprintEntity>
)

data class SprintEntity(
    val id: Int,
    val self: String,
    val state: String,
    val name: String,
    val originBoardId: Int
)