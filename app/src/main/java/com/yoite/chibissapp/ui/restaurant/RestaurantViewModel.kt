package com.yoite.chibissapp.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yoite.chibissapp.model.api.common.Either
import com.yoite.chibissapp.repository.restaurant.RestaurantRepository
import com.yoite.chibissapp.repository.restaurant.data.RestaurantModel
import com.yoite.chibissapp.ui.restaurant.di.RestaurantScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@RestaurantScope
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    private val _progress = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val _errorVisibility = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val _restaurantVisibility = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val _restaurantList = MutableLiveData<List<RestaurantModel>>().apply {
        value = emptyList()
    }

    private var fullRestaurantList = emptyList<RestaurantModel>()

    fun progress(): LiveData<Boolean> = _progress
    fun errorVisibility(): LiveData<Boolean> = _errorVisibility
    fun restaurantVisibility(): LiveData<Boolean> = _restaurantVisibility
    fun restaurantList(): LiveData<List<RestaurantModel>> = _restaurantList

    init {
        loadRestaurant()
    }

    private fun loadRestaurant() {
        _progress.postValue(true)
        _errorVisibility.postValue(false)
        _restaurantVisibility.postValue(false)
        GlobalScope.launch(Dispatchers.Main) {
            repository.getRestaurantList().let {
                _progress.postValue(false)
                when (it) {
                    is Either.Success -> {
                        fullRestaurantList = it.value
                        _errorVisibility.postValue(false)
                        _restaurantVisibility.postValue(true)
                        _restaurantList.postValue(it.value)
                    }
                    is Either.Failure -> {
                        _errorVisibility.postValue(true)
                        _restaurantVisibility.postValue(false)
                    }
                }
            }
        }
    }

    fun onSearchAction(searchString: String) {
        if (searchString.isEmpty()) {
            _restaurantList.postValue(fullRestaurantList)
        } else {
            val searchResult = fullRestaurantList.filter { it.Name.contains(searchString) }.sortedBy { it.Name }
            _restaurantList.postValue(searchResult)
        }
    }
}