package com.example.alkoapp.ui.showalcohol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Alcohol
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class AlcoholsAdapter(
    private var myDataset: ArrayList<Alcohol>,
    val listener: AlcoholClickListener
) :
    RecyclerView.Adapter<AlcoholsAdapter.AlcoholItemHolder>() {


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlcoholItemHolder {
        return AlcoholItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_view_alcohol,
                parent,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: AlcoholItemHolder, position: Int) {

        holder.bind(myDataset[position], listener)
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size


    class AlcoholItemHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.textView1
        val descr = itemView.textView2
        val rate = itemView.textView3

        fun bind(item: Alcohol, listener: AlcoholClickListener) {
            name.text = (item.name)
            descr.text = item.type.toString()
            rate.text = (item.producer)

            itemView.setOnClickListener {
                listener.onRecyclerViewItemClick(itemView, item)
            }
        }

    }
}
