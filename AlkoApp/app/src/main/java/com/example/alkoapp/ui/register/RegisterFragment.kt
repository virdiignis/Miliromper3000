package com.example.alkoapp.ui.register

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.alkoapp.R
import com.example.alkoapp.data.models.User
import com.example.alkoapp.data.network.ApiException
import com.example.alkoapp.ui.account.AccountViewModel
import com.example.alkoapp.util.Coroutines
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {
    private lateinit var registerViewModel: AccountViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerViewModel =
            ViewModelProviders.of(this).get(AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_register, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        register_button.setOnClickListener {
            register()
        }

    }

    private fun register() {
        val u = User(
            username = username.text.toString(),
            first_name = first_name.text.toString(),
            last_name = last_name.text.toString(),
            email = email.text.toString(),
            password = password.text.toString(),
            id = null
        )

        Coroutines.ioThenMain(
            {
                try {
                    registerViewModel.register(u)
                } catch (e: ApiException) {
                    activity!!.runOnUiThread {
                        Toast.makeText(activity, "Api call error: ${e}", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            {
                activity!!.runOnUiThread {
                    Toast.makeText(activity, "Account registered", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}