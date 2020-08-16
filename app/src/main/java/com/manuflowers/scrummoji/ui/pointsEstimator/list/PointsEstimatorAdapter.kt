package com.manuflowers.scrummoji.ui.pointsEstimator.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.CardEstimatorModel

class PointsEstimatorAdapter : RecyclerView.Adapter<PointsEstimatorViewHolder>() {

    private val cardsEstimatorModelList = mutableListOf<CardEstimatorModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointsEstimatorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_points_estimator_view_holder, parent, false)
        return PointsEstimatorViewHolder(view)
    }

    override fun getItemCount(): Int = cardsEstimatorModelList.size

    override fun onBindViewHolder(holder: PointsEstimatorViewHolder, position: Int) {
        holder.bind(cardsEstimatorModelList[position])
    }

    fun addData(cardsEstimatorModelList: List<CardEstimatorModel>) {
        this.cardsEstimatorModelList.addAll(cardsEstimatorModelList)
    }
}