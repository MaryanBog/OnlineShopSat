package com.example.onlineshopsat.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.data.models.Latest
import com.example.data.models.Sale
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var _latestFlow = MutableStateFlow<List<Latest>>(emptyList())
    val latestFlow = _latestFlow.asStateFlow()

    private var _saleFlow = MutableStateFlow<List<Sale>>(emptyList())
    val saleFlow = _saleFlow.asStateFlow()

    private var _receivedError = Channel<Boolean>()
    val receivedError = _receivedError.receiveAsFlow()

    fun loadData(){
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                repository.loadLatestDeals()
            }.fold(
                onSuccess = {
                   loadFlash(it.latest)
                },
                onFailure = {
                    _receivedError.send(true)
                }
            )
        }
    }

    private fun loadFlash(latest: List<Latest>){
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                repository.loadFlashSale()
            }.fold(
                onSuccess = {
                    _saleFlow.emit(it.flashSale)
                    _latestFlow.emit(latest)
                },
                onFailure = {
                    _receivedError.send(true)
                }
            )
        }
    }
}