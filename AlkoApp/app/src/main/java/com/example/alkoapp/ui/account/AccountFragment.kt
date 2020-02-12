package com.example.alkoapp.ui.account

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Credentials
import com.example.alkoapp.util.Coroutines
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {
    private lateinit var accountsViewModel: AccountViewModel
    private lateinit var sharedPreferences: SharedPreferences


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedPreferences = activity!!.getPreferences(MODE_PRIVATE)

        login_button.setOnClickListener { login() }
    }

    private fun login() {
        val credentials = Credentials(login_field.text.toString(), password_field.text.toString())
        Coroutines.ioThenMain(
            {
                val token = accountsViewModel.login(credentials)
                accountsViewModel.me(token.auth_token)
            },
            {
                sharedPreferences.edit().putInt("user_id", it!!.id).apply()
            }
        )
    }
}