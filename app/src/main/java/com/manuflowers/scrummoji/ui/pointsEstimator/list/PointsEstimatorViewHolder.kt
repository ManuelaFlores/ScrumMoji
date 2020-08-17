package com.manuflowers.scrummoji.ui.pointsEstimator.list

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.manuflowers.scrummoji.data.model.CardEstimatorModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_points_estimator_view_holder.view.*

class PointsEstimatorViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(
        cardEstimatorModel: CardEstimatorModel,
        onCardSelected: (cardEstimatorModel: CardEstimatorModel) -> Unit
    ) =
        with(containerView) {
            rightPointsTextView.text = cardEstimatorModel.points.toString()
            leftPointsTextView.text = cardEstimatorModel.points.toString()
            bottomPointsTextView.text = cardEstimatorModel.points.toString()
            emojiImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    this.context,
                    cardEstimatorModel.emojiResource
                )
            )
            this.setOnClickListener {
                onCardSelected.invoke(cardEstimatorModel)
            }
        }
}