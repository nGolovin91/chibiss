package com.yoite.chibissapp.ui.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yoite.chibissapp.R
import com.yoite.chibissapp.repository.restaurant.data.RestaurantModel
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


class RestaurantAdapter(
    var restaurantList: List<RestaurantModel> = emptyList()
): RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RestaurantViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false))

    override fun getItemCount() = restaurantList.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bindView(restaurantList[position])
    }

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val logo = itemView.findViewById<ImageView>(R.id.hits_logo)
        private val name = itemView.findViewById<TextView>(R.id.hits_name)
        private val specialization = itemView.findViewById<TextView>(R.id.restaurant_specialization)
        private val reviews = itemView.findViewById<TextView>(R.id.hits_cost)
        private val deliverTime = itemView.findViewById<TextView>(R.id.restaurant_deliver_time)

        fun bindView(restaurant: RestaurantModel) {
            name.text = restaurant.Name
            specialization.text = getSpecializationString(restaurant.specializations)
            val reit = 5.0f * (restaurant.PositiveReviews.toFloat() / restaurant.ReviewsCount.toFloat())
            val reitText = "%.2f".format(reit)
            reviews.text = "Рейтинг $reitText"
            reviews.setTextColor(
                when {
                    reit >= 4 -> ContextCompat.getColor(itemView.context, R.color.green_light)
                    reit >= 2 -> ContextCompat.getColor(itemView.context, R.color.red_bank)
                    else -> ContextCompat.getColor(itemView.context, R.color.error_red)
                }
            )
            deliverTime.text = restaurant.DeliveryTime.toString()

            val transformation = RoundedCornersTransformation(itemView.resources.getDimensionPixelSize(R.dimen.image_rounded),
                0, RoundedCornersTransformation.CornerType.ALL)
            Picasso.get().load(restaurant.Logo)
                .resize(itemView.resources.getDimensionPixelSize(R.dimen.image_size), itemView.resources.getDimensionPixelSize(R.dimen.image_size))
                .transform(transformation)
                .into(logo)
        }

        private fun getSpecializationString(specializations: List<String>): String {
            var specString = ""
            if (specializations.isNotEmpty()) {
                specString += specializations.first()
                specializations.forEachIndexed { index, s ->
                    if (index > 0) {
                        specString += " / $s"
                    }
                }
            }
            return specString
        }
    }
}