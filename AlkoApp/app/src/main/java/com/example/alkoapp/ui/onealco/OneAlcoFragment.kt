package com.example.alkoapp.ui.onealco

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alkoapp.R


class OneAlcoFragment : Fragment() {

    companion object {
        fun newInstance() = OneAlcoFragment()
    }

    private lateinit var viewModel: OneAlcoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.one_alco_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OneAlcoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
