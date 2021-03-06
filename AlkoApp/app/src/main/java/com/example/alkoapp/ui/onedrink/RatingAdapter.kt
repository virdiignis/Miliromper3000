package com.example.alkoapp.ui.onedrink
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.DrinkRating
import kotlinx.android.synthetic.main.recycler_opinion.view.*

class RatingAdapter(
    private var drinkRatings: ArrayList<DrinkRating>
) :
    RecyclerView.Adapter<RatingAdapter.DrinkRatingItemHolder>() {


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DrinkRatingItemHolder {
        return DrinkRatingItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_opinion,
                parent,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: DrinkRatingItemHolder, position: Int) {

        holder.bind(drinkRatings[position])
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = drinkRatings.size


    class DrinkRatingItemHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.rate_author
        val drinkRating: TextView = itemView.rate_value

        fun bind(item: DrinkRating) {
            name.text = (item.user.toString())
            drinkRating.text = (item.rating)

        }

    }
}