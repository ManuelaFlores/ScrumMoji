package com.manuflowers.scrummoji.ui.pointsEstimator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.CardEstimatorModel
import com.manuflowers.scrummoji.ui.pointsEstimator.list.PointsEstimatorAdapter
import com.manuflowers.scrummoji.ui.pointsEstimator.viewstate.PointsEstimatorError
import com.manuflowers.scrummoji.ui.pointsEstimator.viewstate.PointsEstimatorState
import com.manuflowers.scrummoji.ui.pointsEstimator.viewstate.SuccessFirebaseResponse
import com.manuflowers.scrummoji.ui.storyResultsEstimation.StoryResultsEstimationActivity
import com.manuflowers.scrummoji.utils.toast
import kotlinx.android.synthetic.main.activity_points_estimator.*
import kotlinx.android.synthetic.main.view_card_selected.*
import kotlinx.android.synthetic.main.view_loading.*
import org.koin.android.ext.android.inject

class PointsEstimatorActivity : AppCompatActivity() {

    private val viewModel: PointsEstimatorViewModel by inject()

    private val pointsEstimatorAdapter by lazy {
        PointsEstimatorAdapter(::onCardSelected)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_points_estimator)
        setupListeners()
        viewModel.listenForNewStory()
        setupList()
        subscribeData()
    }

    private fun subscribeData() {
        viewModel.pointsEstimatorStateLiveData.observe(this, Observer {
            onPointsEstimatorStateChanged(it)
        })
    }

    private fun onPointsEstimatorStateChanged(pointsEstimatorState: PointsEstimatorState) {
        when (pointsEstimatorState) {
            is SuccessFirebaseResponse -> {
                viewModel.setCurrentStoryId(pointsEstimatorState.userStory.id)
                titleStoryTextView.text = pointsEstimatorState.userStory.title
                pointsEstimatorAdapter.addData(pointsEstimatorState.data)
                loadingViewContainer.isVisible = false
            }
            is PointsEstimatorError -> {
                this.toast(pointsEstimatorState.error)
            }
        }
    }

    private fun onCardSelected(cardEstimatorModel: CardEstimatorModel) {
        viewModel.updateCurrentCardEstimatorModel(cardEstimatorModel)
        cardSelectedContainer.isVisible = true
        rightPointsSelectedTextView.text = cardEstimatorModel.points.toString()
        leftPointsSelectedTextView.text = cardEstimatorModel.points.toString()
        bottomPointsSelectedTextView.text = cardEstimatorModel.points.toString()
        emojiSelectedImageView.setImageDrawable(
            ContextCompat.getDrawable(this, cardEstimatorModel.emojiResource)
        )
    }

    private fun setupListeners() {
        closeButton.setOnClickListener {
            cardSelectedContainer.isVisible = false
        }

        doneButton.setOnClickListener {
            viewModel.uploadEstimatedStory { startStoryResultsEstimationActivity(this) }
        }
    }

    private fun setupList() {
        pointsEstimatorRecyclerView.adapter = pointsEstimatorAdapter
    }
}

fun startStoryResultsEstimationActivity(from: Context) =
    from.startActivity(Intent(from, StoryResultsEstimationActivity::class.java))