package com.caglaakgul.ecabsandroidchallenge.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caglaakgul.ecabsandroidchallenge.data.remote.ErrorDto
import com.caglaakgul.ecabsandroidchallenge.data.remote.NoConnectionException
import com.caglaakgul.ecabsandroidchallenge.enums.ServiceErrorType
import com.caglaakgul.ecabsandroidchallenge.extension.ifTrue
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

open class BaseViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<ErrorDto>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun <T> launchVMScope(
        flow: Flow<Response<T>>,
        showLoading: Boolean = true,
        showError: Boolean = true,
        data: ((T?) -> Unit)
    ) {
        viewModelScope.launch {
            flow.onStart {
                loadingLiveData.value = showLoading
            }.catch { exception ->
                showError.ifTrue {
                    parseError(exception)
                }
            }.collect { response ->
                if (response.body() == null) {
                    errorLiveData.value = ErrorDto(
                        "Error!",
                        ServiceErrorType.FROM_SERVICE
                    )
                } else {
                    data.invoke(response.body())
                }
                loadingLiveData.value = false
            }
        }
    }

    private fun parseError(exception: Throwable) {
        loadingLiveData.value = false
        errorLiveData.value = when (exception) {
            is NoConnectionException -> {
                ErrorDto("", ServiceErrorType.NO_CONNECTION)
            }

            is HttpException -> {
                when {
                    exception.response()?.errorBody() != null -> {
                        try {
                            val response: ErrorDto? = Gson().fromJson(
                                exception.response()?.errorBody()?.string(),
                                ErrorDto::class.java
                            )
                            ErrorDto(response?.message.orEmpty(), ServiceErrorType.FROM_SERVICE)
                        } catch (e: Exception) {
                            ErrorDto("", ServiceErrorType.SERVICE_UNAVAILABLE)
                        }
                    }

                    exception.code() == 400 -> {
                        ErrorDto("", ServiceErrorType.BAD_REQUEST)
                    }

                    exception.code() == 401 -> {
                        ErrorDto("", ServiceErrorType.UNAUTHORIZED)
                    }

                    exception.code() == 404 -> {
                        ErrorDto("", ServiceErrorType.NOT_FOUND)
                    }

                    exception.code() == 503 ->
                        ErrorDto("", ServiceErrorType.SERVICE_UNAVAILABLE)

                    else ->
                        ErrorDto("", ServiceErrorType.UNKNOWN_ERROR)
                }
            }
            else -> {
                ErrorDto("", ServiceErrorType.UNKNOWN_ERROR)
            }
        }
    }
}