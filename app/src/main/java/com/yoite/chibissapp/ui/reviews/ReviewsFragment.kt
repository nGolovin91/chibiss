package com.yoite.chibissapp.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yoite.chibissapp.ChibissApp
import com.yoite.chibissapp.R
import com.yoite.chibissapp.common.decorationList
import com.yoite.chibissapp.common.vm.injectViewModel
import javax.inject.Inject

class ReviewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ReviewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ChibissApp.diManager.reviewsComponent?.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
    }

    private val adapter = ReviewsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_reviews, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.reviews_progress)
        val hitsError = view.findViewById<TextView>(R.id.reviews_error)

        val hitsRecycler = view.findViewById<RecyclerView>(R.id.reviews_recycler)
        hitsRecycler.layoutManager = LinearLayoutManager(context, GridLayoutManager.VERTICAL, false)
        hitsRecycler.adapter = adapter
        hitsRecycler.addItemDecoration(decorationList(context!!))

        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.reviews_refresh)
        swipeRefresh.setOnRefreshListener {
            viewModel.onRefreshAction()
        }

        viewModel.progressVisibility().observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.errorVisibility().observe(viewLifecycleOwner, Observer {
            hitsError.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.hitsVisibility().observe(viewLifecycleOwner, Observer {
            hitsRecycler.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.hitList().observe(viewLifecycleOwner, Observer {
            swipeRefresh.isRefreshing = false
            adapter.reviewsList = it
            adapter.notifyDataSetChanged()
        })
    }
}