package com.yoite.chibissapp.ui.hits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yoite.chibissapp.ChibissApp
import com.yoite.chibissapp.R
import com.yoite.chibissapp.common.OnItemClickListener
import com.yoite.chibissapp.common.decorationList
import com.yoite.chibissapp.common.vm.injectViewModel
import com.yoite.chibissapp.repository.hits.data.HitsModel
import javax.inject.Inject

class HitsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HitsViewModel

    private val hitsAdapter = HitsAdapter(clickListener = object : OnItemClickListener<HitsModel> {
        override fun onItemClick(model: HitsModel) {
            val myDialogFragment = getLogoDialogInstance(model.ProductImage)
            val manager = activity?.supportFragmentManager
            myDialogFragment.show(manager!!, "myDialog")
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        ChibissApp.diManager.hitsComponent?.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_hits, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.hits_progress)
        val hitsError = view.findViewById<TextView>(R.id.hits_error)

        val hitsRecycler = view.findViewById<RecyclerView>(R.id.hits_recycler)
        hitsRecycler.layoutManager = LinearLayoutManager(context)
        hitsRecycler.adapter = hitsAdapter
        hitsRecycler.addItemDecoration(decorationList(context!!))

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
            hitsAdapter.hitsList = it
            hitsAdapter.notifyDataSetChanged()
        })
    }
}