package com.example.alkoapp.ui.onealco

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.AlcoholRating
import kotlinx.android.synthetic.main.recycler_opinion.view.*

class RatingAdapter(
    private var alcoholRatings: ArrayList<AlcoholRating>
) :
    RecyclerView.Adapter<RatingAdapter.AlcoholRatingItemHolder>() {


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlcoholRatingItemHolder {
        return AlcoholRatingItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_opinion,
                parent,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: AlcoholRatingItemHolder, position: Int) {

        holder.bind(alcoholRatings[position])
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = alcoholRatings.size


    class AlcoholRatingItemHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.rate_author
        val alcoholRating: TextView = itemView.rate_value

        fun bind(item: AlcoholRating) {
            name.text = (item.user.toString())
            alcoholRating.text = (item.rating)

        }

    }
}