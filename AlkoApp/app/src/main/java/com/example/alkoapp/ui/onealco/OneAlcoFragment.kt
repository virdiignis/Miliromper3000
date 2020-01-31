package com.example.alkoapp.ui.onealco

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Alcohol
import com.example.alkoapp.databinding.OneAlcoFragmentBinding
import com.example.alkoapp.ui.onedrink.RateingAdapter
import com.example.alkoapp.ui.showalcohol.AlcoholsAdapter
import kotlinx.android.synthetic.main.one_alco_fragment.*
import kotlinx.android.synthetic.main.show_alcohols_fragment.*


class OneAlcoFragment(val itemAlcohol: Alcohol) : Fragment() {

    private lateinit var viewModel: OneAlcoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: OneAlcoFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.one_alco_fragment, container, false)
        binding.alcohol = itemAlcohol
        return binding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OneAlcoViewModel::class.java)

        viewModel.getRatings(itemAlcohol.id)
        viewModel.ratings.observe(viewLifecycleOwner, Observer { ratings ->
            alcohol_rate_recycler_view!!.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = RateingAdapter(ratings)
            }
        })

    }


}


