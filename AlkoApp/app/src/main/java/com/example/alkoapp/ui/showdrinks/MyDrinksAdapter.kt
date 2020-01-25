package com.example.alkoapp.ui.showdrinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Drink
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class MyDrinksAdapter(
    private var myDataset: ArrayList<Drink>
) :
    RecyclerView.Adapter<MyDrinksAdapter.DrinkHolder>() {


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DrinkHolder {
        return DrinkHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: DrinkHolder, position: Int) {
        when (holder) {

            is DrinkHolder -> {
                holder.bind(myDataset[position])
            }

        }
    }

    fun submitList(drinkList: ArrayList<Drink>){
        myDataset = drinkList
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size


    class DrinkHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val drinkId = itemView.textView1
        val drinkName = itemView.textView2
        val drinkRaiting = itemView.textView3

        fun bind(drinkItem: Drink) {
            drinkId.text = (drinkItem.id)
            drinkName.text = (drinkItem.name)
            drinkRaiting.text = (drinkItem.rating)
        }

    }
}