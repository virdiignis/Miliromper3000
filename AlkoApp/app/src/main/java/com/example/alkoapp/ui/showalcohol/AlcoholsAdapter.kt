package com.example.alkoapp.ui.showalcohol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Alcohol

import com.example.alkoapp.util.RecyclerViewClickListener
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class AlcoholsAdapter(
    private var myDataset: ArrayList<Alcohol>
//    ,private val listener: RecyclerViewClickListener
) :
    RecyclerView.Adapter<AlcoholsAdapter.AlcoholItemHolder>() {


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlcoholItemHolder {
        return AlcoholItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: AlcoholItemHolder, position: Int) {
        when (holder) {

            is AlcoholItemHolder -> {
                holder.bind(myDataset[position])
            }

        }
    }

    fun submitList(list: ArrayList<Alcohol>){
        myDataset = list
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size


    class AlcoholItemHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.textView1
        val descr = itemView.textView2
        val rate = itemView.textView3


//        TODO:
        fun bind(item: Alcohol) {
            name.text = (item.name)
            descr.text = (item.description as CharSequence?)
            rate.text = (item.producer)
        }

    }
}