package com.riyadhfoods1.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.riyadhfoods1.mvvmsampleapp.R
import com.riyadhfoods1.mvvmsampleapp.util.Coroutines
import com.riyadhfoods1.mvvmsampleapp.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(),KodeinAware {
    override val kodein by kodein()

    val factory: QuoteViewModelFactory by instance()
    companion object {
        fun newInstance() = QuotesFragment()
    }

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(QuotesViewModel::class.java)
        Coroutines.main {
          val quote =   viewModel.quotes.await()
            quote.observe(this, Observer {
                context?.toast(it.size.toString())
            })
        }

    }

}
