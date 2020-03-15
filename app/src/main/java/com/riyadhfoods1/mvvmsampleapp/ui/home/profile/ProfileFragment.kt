package com.riyadhfoods1.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.riyadhfoods1.mvvmsampleapp.R
import com.riyadhfoods1.mvvmsampleapp.databinding.ProfileFragmentBinding
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance


class ProfileFragment : Fragment(),KodeinAware{

    override val kodein by kodein()
    val factory:ProfileViewModelFactory by instance()


    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding:ProfileFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.profile_fragment,container,false)

        viewModel = ViewModelProviders.of(this,factory).get(ProfileViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }


}
