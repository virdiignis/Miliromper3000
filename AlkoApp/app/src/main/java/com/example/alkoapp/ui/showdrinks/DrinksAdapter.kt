package com.example.alkoapp.ui.showdrinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Drink
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class DrinksAdapter(
    private var myDataset: ArrayList<Drink>,
    val listener: DrinkClickListener
) : Filterable,
    RecyclerView.Adapter<DrinksAdapter.DrinkHolder>() {

    private val myDatasetFull = myDataset

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DrinkHolder {
        return DrinkHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_item,
                parent,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: DrinkHolder, position: Int) {
        holder.bind(myDataset[position], listener)

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size


    class DrinkHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val drinkId = itemView.textView1
        val drinkName = itemView.textView2
        val drinkRating = itemView.textView3

        fun bind(item: Drink, listener: DrinkClickListener) {
            drinkId.text = (item.name)
            drinkName.text = (item.description)
            drinkRating.text = (item.instruction)

            itemView.setOnClickListener {
                listener.onRecyclerViewItemClick(itemView, item)
            }
        }
    }

    private var valueFilter: ValueFilter = ValueFilter()

    inner class ValueFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            if (constraint != null && constraint.isNotEmpty()) {
                val filterList: MutableList<Drink> = ArrayList()
                for (i in 0 until myDatasetFull.size) {
                    if (myDatasetFull[i].name.toUpperCase().contains(constraint.toString().toUpperCase())
                        || myDatasetFull[i].description.toUpperCase().contains(constraint.toString().toUpperCase())
                    ) {
                        filterList.add(myDatasetFull[i])
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = myDataset.size
                results.values = myDataset
            }
            return results
        }

        override fun publishResults(
            constraint: CharSequence?,
            results: FilterResults
        ) {
            myDataset = results.values as ArrayList<Drink>
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return valueFilter
    }
}