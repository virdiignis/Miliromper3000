package com.example.alkoapp.ui.addalcohol

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.alkoapp.data.models.Country
import com.example.alkoapp.data.models.Producer
import com.example.alkoapp.data.models.Type

class SpinAdapterProducer( val context: Context?, private var producers : ArrayList<Producer>) : BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getItem(position: Int): Any {
        return producers[position].name
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getCount(): Int {
       return producers.size
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View{
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false) as TextView
        view.text = producers[position].name
        return view
    }

}
class SpinAdapterCountry( val context: Context?, private var producers : ArrayList<Country>) : BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getItem(position: Int): Any {
        return producers[position].name
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getCount(): Int {
        return producers.size
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View{
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false) as TextView
        view.text = producers[position].name
        return view
    }

}


class SpinAdapterType( val context: Context?, private var types : ArrayList<Type>) : BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getItem(position: Int): Any {
        return types[position]
    }

    override fun getItemId(position: Int): Long {
        return types[position].id.toLong()
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getCount(): Int {
        return types.size
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View{
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false) as TextView
        view.text = types[position].toString()
        return view
    }

}

