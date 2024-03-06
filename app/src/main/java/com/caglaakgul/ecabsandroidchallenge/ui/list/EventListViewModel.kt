package com.caglaakgul.ecabsandroidchallenge.ui.list

import androidx.lifecycle.MutableLiveData
import com.caglaakgul.ecabsandroidchallenge.base.BaseViewModel
import com.caglaakgul.ecabsandroidchallenge.data.model.Event
import com.caglaakgul.ecabsandroidchallenge.data.repository.EventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(private val repository: EventsRepository) :
    BaseViewModel() {

    val eventListLiveData = MutableLiveData<List<Event>>()

    init {
        getEventList()
    }

     fun getEventList() {
        launchVMScope(repository.getEventList()) { response ->
            response?.let { events ->
                eventListLiveData.value = events
            }
        }
    }
}