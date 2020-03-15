package com.riyadhfoods1.mvvmsampleapp.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.riyadhfoods1.mvvmsampleapp.R
import com.riyadhfoods1.mvvmsampleapp.data.database.AppDatabase
import com.riyadhfoods1.mvvmsampleapp.data.database.entities.UserEntity
import com.riyadhfoods1.mvvmsampleapp.data.network.MyApi
import com.riyadhfoods1.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.riyadhfoods1.mvvmsampleapp.data.repository.UserRepository
import com.riyadhfoods1.mvvmsampleapp.databinding.ActivityLoginBinding
import com.riyadhfoods1.mvvmsampleapp.ui.home.HomeActivity
import com.riyadhfoods1.mvvmsampleapp.util.hide
import com.riyadhfoods1.mvvmsampleapp.util.show
import com.riyadhfoods1.mvvmsampleapp.util.snackbar
import com.riyadhfoods1.mvvmsampleapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),AuthListner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var networkConnectionInterceptor = NetworkConnectionInterceptor(this)

        var api =MyApi(networkConnectionInterceptor)
        var db = AppDatabase(this)
        var repository = UserRepository(api,db)
        val factory = AuthViewModelFactory(repository)

        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListner = this

        viewModel.getLoggedInuser().observe(this, Observer {UserEntity ->
            if (UserEntity != null) {
              Intent(this,HomeActivity::class.java).also {
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
        root_layout.snackbar(message)
        progress_bar.hide()
    }
}
