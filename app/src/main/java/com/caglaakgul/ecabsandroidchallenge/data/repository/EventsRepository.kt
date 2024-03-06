package com.caglaakgul.ecabsandroidchallenge.data.repository

import com.caglaakgul.ecabsandroidchallenge.data.model.Event
import com.caglaakgul.ecabsandroidchallenge.data.model.Repository
import com.caglaakgul.ecabsandroidchallenge.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class EventsRepository @Inject constructor(private val api: ApiService) {
    fun getEventList(): Flow<Response<List<Event>>> = flow {
        emit(api.getEvents())
    }.flowOn(Dispatchers.IO)

     fun getRepository(owner: String, repo: String): Flow<Response<Repository>> = flow {
        emit(api.getRepository(owner, repo))
    }.flowOn(Dispatchers.IO)
}