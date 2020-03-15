package com.riyadhfoods1.mvvmsampleapp.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.riyadhfoods1.mvvmsampleapp.R
import com.riyadhfoods1.mvvmsampleapp.data.database.entities.UserEntity

import com.riyadhfoods1.mvvmsampleapp.ui.home.HomeActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import androidx.lifecycle.Observer
import com.riyadhfoods1.mvvmsampleapp.databinding.ActivitySignupBinding
import com.riyadhfoods1.mvvmsampleapp.util.hide
import com.riyadhfoods1.mvvmsampleapp.util.show
import com.riyadhfoods1.mvvmsampleapp.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.progress_bar
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(),KodeinAware,AuthListner {

    override val kodein by kodein()
    private val factory:AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListner = this

        viewModel.getLoggedInuser().observe(this, Observer {UserEntity ->
            if (UserEntity != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(userEntity: UserEntity) {

//        loginResponse.observe(this, Observer {
//            toast(it)
//            progress_bar.hide()
//        })
        progress_bar.hide()
//        root_layout.snackbar("${userEntity.name}")
    }

    override fun onFaliure(message: String) {
        signup_root_layout.snackbar(message)
        progress_bar.hide()
    }
}
