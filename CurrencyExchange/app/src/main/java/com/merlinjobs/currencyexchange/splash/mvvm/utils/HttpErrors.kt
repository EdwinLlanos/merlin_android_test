package com.merlinjobs.currencyexchange.splash.mvvm.utils

import retrofit2.HttpException

object HttpErrors {
    //val DEFAULT_HTTP_ERROR_MESSAGE = R.string.default_error_message
    val DEFAULT_HTTP_ERROR_MESSAGE = 5

    fun httpExceptionToErrorMessage(exception: HttpException): Int {
        // when (exception.code()) {
        //     404 -> ...
        return DEFAULT_HTTP_ERROR_MESSAGE
    }
}