package com.manuflowers.scrummoji.ui.pointsEstimator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.ui.pointsEstimator.list.PointsEstimatorAdapter
import kotlinx.android.synthetic.main.activity_points_estimator.*

class PointsEstimatorActivity : AppCompatActivity() {

    private val pointsEstimatorAdapter by lazy {
        PointsEstimatorAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_points_estimator)
    }

    private fun setupList() {
        pointsEstimatorRecyclerView.adapter = pointsEstimatorAdapter
    }
}