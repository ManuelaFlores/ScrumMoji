package com.manuflowers.domain.sprints.model

data class SprintEntity(
    val id: Int,
    val self: String,
    val state: String,
    val name: String,
    val originBoardId: Int
)