package com.example.alkoapp.ui.adddrink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.alkoapp.R
import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table
import kotlinx.android.synthetic.main.add_drink_fragment.*


class AddDrinkFragment : Fragment() {

    companion object {
        fun newInstance() = AddDrinkFragment()
    }

    private lateinit var viewModel: AddDrinkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_drink_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddDrinkViewModel::class.java)


        val row: TableRow =
            LayoutInflater.from(context).inflate(R.layout.ingredient_row) as TableRow

        ingredientsTable.addView(row)
        // TODO: Use the ViewModel
    }

}
