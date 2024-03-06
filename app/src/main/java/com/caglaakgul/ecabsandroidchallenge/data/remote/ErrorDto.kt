package com.caglaakgul.ecabsandroidchallenge.data.remote

import com.caglaakgul.ecabsandroidchallenge.enums.ServiceErrorType

data class ErrorDto(
    var message: String,
    var errorType : ServiceErrorType
)