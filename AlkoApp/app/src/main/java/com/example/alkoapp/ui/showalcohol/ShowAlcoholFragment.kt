package com.example.alkoapp.ui.showalcohol

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alkoapp.R

class ShowAlcoholFragment : Fragment() {

    companion object {
        fun newInstance() = ShowAlcoholFragment()
    }

    private lateinit var viewModel: ShowAlcoholViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.show_alcohol_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShowAlcoholViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
