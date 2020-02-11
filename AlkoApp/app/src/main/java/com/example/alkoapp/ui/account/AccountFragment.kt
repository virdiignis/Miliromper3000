package com.example.alkoapp.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.alkoapp.R

class AccountFragment : Fragment() {

    private lateinit var accountsViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountsViewModel =
            ViewModelProviders.of(this).get(AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_account, container, false)
        return root
    }
}