package com.manuflowers.scrummoji.ui.roomLogin.viewstate

sealed class RoomLoginViewState
object UserLoggedIn : RoomLoginViewState()
object InvalidUserNickname : RoomLoginViewState()
object InvalidPassword : RoomLoginViewState()
object ValidUserNickname : RoomLoginViewState()
object ValidPassword : RoomLoginViewState()