package com.yoite.chibissapp.ui.hits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yoite.chibissapp.model.api.common.Either
import com.yoite.chibissapp.repository.hits.HitsRepository
import com.yoite.chibissapp.repository.hits.data.HitsModel
import com.yoite.chibissapp.ui.hits.di.HitsScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HitsScope
class HitsViewModel @Inject constructor(
    private val repository: HitsRepository
): ViewModel() {

    private val _progressVisibility = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val _errorVisibility = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val _hitsVisibility = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val _hitList = MutableLiveData<List<HitsModel>>().apply {
        value = emptyList()
    }

    fun progressVisibility(): LiveData<Boolean> = _progressVisibility
    fun errorVisibility(): LiveData<Boolean> = _errorVisibility
    fun hitsVisibility(): LiveData<Boolean> = _hitsVisibility
    fun hitList(): LiveData<List<HitsModel>> = _hitList

    init {
        loadHitsList()
    }

    private fun loadHitsList() {
        _progressVisibility.postValue(true)
        _errorVisibility.postValue(false)
        _hitsVisibility.postValue(false)
        GlobalScope.launch(Dispatchers.Main) {
            repository.getHitsList().let {
                _progressVisibility.postValue(false)
                when (it) {
                    is Either.Success -> {
                        _errorVisibility.postValue(false)
                        _hitsVisibility.postValue(true)
                        _hitList.postValue(it.value)
                    }
                    is Either.Failure -> {
                        _errorVisibility.postValue(true)
                        _hitsVisibility.postValue(false)
                    }
                }
            }
        }
    }
}