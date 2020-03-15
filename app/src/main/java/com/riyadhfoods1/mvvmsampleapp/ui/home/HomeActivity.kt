package com.riyadhfoods1.mvvmsampleapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.riyadhfoods1.mvvmsampleapp.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        val navigation = Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupWithNavController(nav_view,navigation)
        NavigationUI.setupActionBarWithNavController(this,navigation,drawerLayout)


    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.fragment), drawerLayout)
    }

}
