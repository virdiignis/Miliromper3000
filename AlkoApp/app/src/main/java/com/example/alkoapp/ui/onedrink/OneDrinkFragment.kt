package com.example.alkoapp.ui.onedrink

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Drink
import com.example.alkoapp.data.models.DrinkRating
import com.example.alkoapp.databinding.OneDrinkFragmentBinding
import com.example.alkoapp.util.Coroutines
import kotlinx.android.synthetic.main.one_drink_fragment.*
import kotlinx.coroutines.Job


class OneDrinkFragment(val itemDrink: Drink) : Fragment() {

    private lateinit var viewModel: OneDrinkViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: OneDrinkFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.one_drink_fragment, container, false)
            binding.drink = itemDrink
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OneDrinkViewModel::class.java)

        viewModel.getRatings(itemDrink.id)


        viewModel.ratings.observe(viewLifecycleOwner, Observer { ratings ->
            drink_rate_recycler_view!!.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = RatingAdapter(ratings)

                ratingBar.setOnRatingBarChangeListener { _, _, _ -> }
                val user_rating =
                    viewModel.ratings.value?.find { it.user == 1 } // TODO: change this when users are working xD :/
                if (user_rating != null) {
                    ratingBar.rating = user_rating.rating!!.toFloat()
                    favouriteButton.isChecked = user_rating.favourite
                }

                ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                    rate(ratingBar, rating, fromUser)
                }
            }
        })

        favouriteButton.setOnClickListener {
            fav()
        }
    }


    private fun rate(ratingBar: RatingBar, rating: Float, fromUser: Boolean) {
        val old_rating =
            viewModel.ratings.value?.find { it.user == 1 } // TODO: change this when users are working xD :/
        lateinit var job: Job

        if (old_rating == null) {
            val arating = DrinkRating(
                drink = itemDrink.id,
                rating = rating.toString(),
                user = 1, // TODO: change this when we have login XDD,
                favourite = false,
                id = null,
                comment = null
            )

            job = viewModel.addRating(arating)


        } else {
            val arating = DrinkRating(
                drink = itemDrink.id,
                rating = rating.toString(),
                user = old_rating.user,
                favourite = old_rating.favourite,
                id = old_rating.id,
                comment = old_rating.comment
            )

            job = viewModel.changeRating(old_rating.id!!, arating)
        }

        job.invokeOnCompletion {
            Coroutines.ioThenMain(
                {
                    viewModel.getDrink(itemDrink.id)
//                    viewModel.getRatings(itemAlcohol.id)
                },
                {
                    //                    alcohol_rate_recycler_view.adapter!!.notifyDataSetChanged()
                    super.getFragmentManager()?.popBackStack()
                    super.getFragmentManager()?.beginTransaction()
                        ?.replace(id, OneDrinkFragment(it!!))?.addToBackStack("app")?.commit()
                })
        }
    }

    private fun fav() {
        val rating =
            viewModel.ratings.value?.find { it.user == 1 } // TODO: change this when users are working xD :/
        if (rating == null) {
            val newRating = DrinkRating(
                drink = itemDrink.id,
                favourite = true,
                rating = null,
                user = 1,
                id = null,
                comment = null
            )
            viewModel.addRating(newRating)
        } else {
            val newRating = DrinkRating(
                id = rating.id,
                drink = rating.drink,
                favourite = !rating.favourite,
                rating = rating.rating,
                user = rating.user,
                comment = rating.comment
            )
            viewModel.changeRating(rating.id!!, newRating)
        }

    }
}
