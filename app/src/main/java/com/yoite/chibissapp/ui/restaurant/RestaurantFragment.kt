package com.yoite.chibissapp.ui.restaurant

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoite.chibissapp.ChibissApp
import com.yoite.chibissapp.R
import com.yoite.chibissapp.common.decorationList
import com.yoite.chibissapp.common.vm.injectViewModel
import javax.inject.Inject

class RestaurantFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var restaurantViewModel: RestaurantViewModel

    private val restaurantAdapter = RestaurantAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        ChibissApp.diManager.restaurantComponent?.inject(this)
        super.onCreate(savedInstanceState)
        restaurantViewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_restaurant, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val restaurantError = view.findViewById<TextView>(R.id.restaurant_error)
        val progress = view.findViewById<ProgressBar>(R.id.restaurant_progress)

        val restaurantRecycler = view.findViewById<RecyclerView>(R.id.restaurant_recycler)
        restaurantRecycler.layoutManager = LinearLayoutManager(context)
        restaurantRecycler.adapter = restaurantAdapter
        restaurantRecycler.addItemDecoration(decorationList(context!!))

        restaurantViewModel.errorVisibility().observe(viewLifecycleOwner, Observer {
            restaurantError.visibility = if (it) View.VISIBLE else View.GONE
        })

        restaurantViewModel.progress().observe(viewLifecycleOwner, Observer {
            progress.visibility = if (it) View.VISIBLE else View.GONE
        })

        restaurantViewModel.restaurantVisibility().observe(viewLifecycleOwner, Observer {
            restaurantRecycler.visibility = if (it) View.VISIBLE else View.GONE
        })

        restaurantViewModel.restaurantList().observe(viewLifecycleOwner, Observer {
            restaurantAdapter.restaurantList = it
            restaurantAdapter.notifyDataSetChanged()
        })

        view.findViewById<EditText>(R.id.search_input).addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                restaurantViewModel.onSearchAction(p0.toString())
            }
        })

        view.findViewById<TextView>(R.id.search_cancel).setOnClickListener {
            restaurantViewModel.onSearchAction("")
        }
    }
}