package com.manuflowers.scrummoji.ui.roomLogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.ui.pointsEstimator.PointsEstimatorActivity
import com.manuflowers.scrummoji.ui.roomLogin.viewstate.*
import kotlinx.android.synthetic.main.activity_room_login.*
import org.koin.android.ext.android.inject

class RoomLoginActivity : AppCompatActivity() {

    private val viewModel: RoomLoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_login)
        setupListeners()
        subscribeToData()
    }

    private fun subscribeToData() {
        viewModel.loginViewStateLiveData.observe(this, Observer {
            onRoomLoginViewStateChanged(it)
        })
    }

    private fun onRoomLoginViewStateChanged(roomLoginViewState: RoomLoginViewState) {
        when (roomLoginViewState) {
            UserLoggedIn -> navigateToPointsEstimatorActivity()
            InvalidUserNickname -> setNicknameError()
            InvalidPassword -> setRoomIdError()
            ValidUserNickname -> removeUserNicknameError()
            ValidPassword -> removeRoomIdError()
        }
    }

    private fun setupListeners() {
        joinRoomButton.setOnClickListener {
            viewModel.checkUserCredentials(
                nickName = getUserNickname(),
                roomId = getRoomIdData()
            )
        }
    }

    private fun navigateToPointsEstimatorActivity() {
        finish()
        startPointsEstimatorActivity(this)
    }

    private fun getUserNickname(): String {
        return nicknameEditText.text?.trim().toString()
    }

    private fun getRoomIdData(): String {
        return roomIdEditText.text?.trim().toString()
    }

    private fun setRoomIdError() {
        roomIdTextInputLayout.error = "Your room id needs to have at least 15 characters"
    }

    private fun setNicknameError() {
        nicknameTextInputLayout.error = "Your nickname needs to have at least 4 characters"
    }

    private fun removeUserNicknameError() {
        nicknameTextInputLayout.error = null
    }

    private fun removeRoomIdError() {
        roomIdTextInputLayout.error = null
    }
}

fun startPointsEstimatorActivity(from: Context) =
    from.startActivity(Intent(from, PointsEstimatorActivity::class.java))