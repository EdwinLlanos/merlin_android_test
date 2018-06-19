package com.merlinjobs.currencyexchange.splash.mvvm.net

import com.merlinjobs.currencyexchange.splash.mvvm.net.response.RepoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeService {

    @GET("repos/{owner}/{repo}")
    fun getRepo(
            @Path("owner") owner: String,
            @Path("repo") repo: String
    ): Observable<RepoResponse>

    companion object {
        val BASE_URL = "https://api.github.com/"
    }
}