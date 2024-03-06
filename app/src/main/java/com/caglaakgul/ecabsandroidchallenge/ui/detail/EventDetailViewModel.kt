package com.caglaakgul.ecabsandroidchallenge.ui.detail

import com.caglaakgul.ecabsandroidchallenge.base.BaseViewModel
import com.caglaakgul.ecabsandroidchallenge.data.model.Event
import com.caglaakgul.ecabsandroidchallenge.data.repository.EventsRepository
import com.caglaakgul.ecabsandroidchallenge.extension.splitStringBySlash
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(private val repository: EventsRepository): BaseViewModel() {
    private val _repositoryUrl = MutableStateFlow<String>("")
    val repositoryUrl: StateFlow<String> = _repositoryUrl

    private val _event = MutableStateFlow<Event?>(null)
    val event: StateFlow<Event?> = _event

    fun setEvent(event: Event) {
        _event.value = event
        val (owner, repo) = event.repo?.name?.splitStringBySlash() ?: Pair("", "")
        getRepository(owner, repo)
    }

    fun getRepository(owner: String, repo: String){
        launchVMScope(repository.getRepository(owner, repo)) { response ->
            response?.let { repo ->
                _repositoryUrl.value = repo.htmlUrl ?: ""
            }
        }
    }
}