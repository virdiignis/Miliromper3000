package com.example.alkoapp.ui.account

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.alkoapp.R
import com.example.alkoapp.data.models.Credentials
import com.example.alkoapp.data.network.ApiException
import com.example.alkoapp.util.Coroutines
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {
    private lateinit var accountsViewModel: AccountViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private var logged_in = false


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
        if (sharedPreferences.getInt("user_id", -1) > 0) {
            Coroutines.ioThenMain({
                try {
                    val user = accountsViewModel.me(
                        sharedPreferences.getString(
                            "token",
                            "dummytoken"
                        )!!
                    )
                    logged_in = true
                    activity!!.runOnUiThread {
                        login_field.setText(user.username)
                        password_field.visibility = View.INVISIBLE
                        login_button.text = "Logout"
                    }
                } catch (e: ApiException) {
                    sharedPreferences.edit().remove("token").remove("user_id").apply()
                }

            },
                {

                }
            )

        }


        login_button.setOnClickListener { login() }
    }

    private fun login() {
        if (!logged_in) {
            val credentials =
                Credentials(login_field.text.toString(), password_field.text.toString())
            Coroutines.ioThenMain(
                {
                    try {
                        val token = accountsViewModel.login(credentials)
                        sharedPreferences.edit().putString("token", token.auth_token).apply()
                        val user = accountsViewModel.me(token.auth_token)
                        sharedPreferences.edit().putInt("user_id", user.id!!).apply()
                        logged_in = true
                        activity!!.runOnUiThread {
                            password_field.visibility = View.INVISIBLE
                            login_button.text = "Logout"
                        }
                    } catch (e: ApiException) {
                        activity?.runOnUiThread {
                            Toast.makeText(
                                activity,
                                "Cannot login. Check your credentials.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },
                {
                }
            )


        } else {
            sharedPreferences.edit().remove("token").remove("user_id").apply()
            password_field.visibility = View.VISIBLE
            login_button.text = "Login"
            logged_in = false
        }
    }
}