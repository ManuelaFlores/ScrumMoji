package com.manuflowers.scrummoji

class JiraRepositoryImpl(
    val jiraApi: JiraApi
) {

    suspend fun getSprints(): GetSprintsResponse = jiraApi.getSprints()
}