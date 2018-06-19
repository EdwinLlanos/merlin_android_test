package com.merlinjobs.currencyexchange.splash.mvvm.net

import com.merlinjobs.currencyexchange.splash.mvvm.net.response.RepoResponse
import io.reactivex.Observable
import javax.inject.Inject

class ApiClient @Inject constructor(private val exchangeService: ExchangeService) {
    fun getRepo(owner: String, repo: String): Observable<RepoResponse> {
        return exchangeService.getRepo(owner, repo)
    }
}