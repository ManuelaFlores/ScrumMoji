package com.manuflowers.scrummoji.ui.roomLogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
        setSupportActionBar(roomLoginToolbar)
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
        roomIdTextInputLayout.error = getString(R.string.room_id_login_error)
    }

    private fun setNicknameError() {
        nicknameTextInputLayout.error = getString(R.string.room_login_nickname_error)
    }

    private fun removeUserNicknameError() {
        nicknameTextInputLayout.error = null
    }

    private fun removeRoomIdError() {
        roomIdTextInputLayout.error = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

fun startPointsEstimatorActivity(from: Context) =
    from.startActivity(Intent(from, PointsEstimatorActivity::class.java))