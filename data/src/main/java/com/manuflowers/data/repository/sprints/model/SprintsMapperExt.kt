package com.manuflowers.data.repository.sprints.model

import com.manuflowers.domain.sprints.model.SprintEntity
import com.manuflowers.domain.sprints.model.SprintsEntity
import com.manuflowers.domain.sprints.model.UserCredentialEntity

fun UserCredentialEntity.toData(): UserCredentialData = UserCredentialData(
    userName = this.userName,
    password = this.password
)

fun UserCredentialData.toDomain(): UserCredentialEntity = UserCredentialEntity(
    userName = this.userName,
    password = this.password
)

fun SprintsEntity.toData(): SprintsData = SprintsData(
    maxResults = this.maxResults,
    startAt = this.startAt,
    isLast = this.isLast,
    values = this.values.map { it.toData() }
)

fun SprintsData.toEntity(): SprintsEntity = SprintsEntity(
    maxResults = this.maxResults,
    startAt = this.startAt,
    isLast = this.isLast,
    values = this.values.map { it.toEntity() }
)

fun SprintEntity.toData(): SprintData = SprintData(
    id = this.id,
    self = this.self,
    state = this.state,
    name = this.name,
    originBoardId = this.originBoardId
)

fun SprintData.toEntity(): SprintEntity = SprintEntity(
    id = this.id,
    self = this.self,
    state = this.state,
    name = this.name,
    originBoardId = this.originBoardId
)