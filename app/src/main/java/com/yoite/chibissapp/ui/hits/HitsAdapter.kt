package com.yoite.chibissapp.ui.hits

import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yoite.chibissapp.R
import com.yoite.chibissapp.common.OnItemClickListener
import com.yoite.chibissapp.repository.hits.data.HitsModel
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


class HitsAdapter(
    var hitsList: List<HitsModel> = emptyList(),
    var clickListener: OnItemClickListener<HitsModel>? = null
): RecyclerView.Adapter<HitsAdapter.HitsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HitsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_hits, parent, false))

    override fun getItemCount() = hitsList.size

    override fun onBindViewHolder(holder: HitsViewHolder, position: Int) {
        holder.bindViewHolder(hitsList[position])
    }

    inner class HitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val logo = itemView.findViewById<ImageView>(R.id.hits_logo)
        private val name = itemView.findViewById<TextView>(R.id.hits_name)
        private val cost = itemView.findViewById<TextView>(R.id.hits_cost)
        private val location = itemView.findViewById<TextView>(R.id.hits_restaurant)

        fun bindViewHolder(model: HitsModel) {
            name.text = model.ProductName
            cost.text = "${model.ProductPrice}₽"
            location.text = model.RestaurantName

            val transformation = RoundedCornersTransformation(itemView.resources.getDimensionPixelSize(R.dimen.image_rounded),
                0, RoundedCornersTransformation.CornerType.ALL)
            Picasso.get().load(model.ProductImage)
                .resize(itemView.resources.getDimensionPixelSize(R.dimen.image_size), itemView.resources.getDimensionPixelSize(R.dimen.image_size))
                .transform(transformation)
                .into(logo)

            logo.setOnLongClickListener {
                clickListener?.onItemClick(model)
                true
            }
        }
    }
}