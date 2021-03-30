package com.manuflowers.domain.sprints.model

data class SprintsEntity(
    val maxResults: Int,
    val startAt: String,
    val isLast: Boolean,
    val values: List<SprintEntity>
)