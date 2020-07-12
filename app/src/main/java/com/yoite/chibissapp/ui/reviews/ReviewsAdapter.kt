package com.yoite.chibissapp.ui.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yoite.chibissapp.R
import com.yoite.chibissapp.common.dateLongToString
import com.yoite.chibissapp.repository.reviews.model.ReviewModel


class ReviewsAdapter(
    var reviewsList: List<ReviewModel> = emptyList()
): RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReviewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_reviews, parent, false))

    override fun getItemCount() = reviewsList.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bindViewHolder(reviewsList[position])
    }

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.reviews_title)
        private val description = itemView.findViewById<TextView>(R.id.reviews_description)
        private val date = itemView.findViewById<TextView>(R.id.reviews_date)

        fun bindViewHolder(model: ReviewModel) {
            title.text = "${model.UserFIO} о ресторане ${model.RestaurantName}"
            description.text = model.Message
            date.text = dateLongToString(model.DateAdded)
        }
    }
}