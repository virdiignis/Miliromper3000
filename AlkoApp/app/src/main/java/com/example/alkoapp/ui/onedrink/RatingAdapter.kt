package com.example.alkoapp.ui.onedrink
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Rate
import kotlinx.android.synthetic.main.recycler_opinion.view.*
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class RateingAdapter(
    private var rates: ArrayList<Rate>
) :
    RecyclerView.Adapter<RateingAdapter.RateItemHolder>() {


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RateItemHolder {
        return RateItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_opinion,
                parent,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RateItemHolder, position: Int) {

        holder.bind(rates[position])
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = rates.size


    class RateItemHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.rate_author
        val rate: TextView = itemView.rate_value

        fun bind(item: Rate) {
            name.text = (item.user.toString())
            rate.text = (item.rating)

        }

    }
}