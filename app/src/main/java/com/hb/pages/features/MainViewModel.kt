package com.hb.pages.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import com.hb.pages.api.ApiResponse
import com.hb.pages.api.google.mapper.GoogleBooksVolumeMapper
import com.hb.pages.api.google.model.GoogleBooksApiResponse
import com.hb.pages.extensions.trigger
import com.hb.pages.model.Book
import com.hb.pages.usecases.book.SearchBookUseCase
import com.hb.pages.viewmodel.BaseViewModel

class MainViewModel(
    private val searchBookUseCase: SearchBookUseCase,
    private val googleBooksVolumeMapper: GoogleBooksVolumeMapper
) : BaseViewModel() {

    private val _state = MutableLiveData<MainActivityState>(MainActivityState.FirstSearch)
    val state: LiveData<MainActivityState> get() = _state

    suspend fun search(query: String) {
        if (query.isEmpty()) {
            _state.value = MainActivityState.NoResults
            return
        }
        when (val apiResponse = searchBookUseCase.search(query)) {
            is ApiResponse.Success<*> -> {
                val data = apiResponse.data as GoogleBooksApiResponse
                val results = data.items?.map { googleBooksVolumeMapper.map(it) } ?: emptyList()
                _state.value =  if (results.isNotEmpty()) {
                    MainActivityState.Results(results)
                } else {
                    MainActivityState.NoResults
                }
            }
            is ApiResponse.Error<*> -> {
                _state.value = MainActivityState.ConnectionError
            }
        }
    }
}