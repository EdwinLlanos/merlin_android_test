package com.merlinjobs.currencyexchange.data.network.routes

import com.merlinjobs.currencyexchange.data.FIXER_IO_ACCESS_KEY
import com.merlinjobs.currencyexchange.data.network.models.APICurrencyResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IConvertRoute {

    @GET("latest?access_key=$FIXER_IO_ACCESS_KEY")
    fun getCurrencyConversion(@Query("symbols") symbols: String): Observable<APICurrencyResponse>

}