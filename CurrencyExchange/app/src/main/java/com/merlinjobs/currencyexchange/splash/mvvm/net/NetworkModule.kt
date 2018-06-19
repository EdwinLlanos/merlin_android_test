package com.merlinjobs.currencyexchange.splash.mvvm.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesExchangeClient(gson: Gson): ApiClient {
        val retrofit = Retrofit.Builder()
                .baseUrl(ExchangeService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val gitHubService = retrofit.create(ExchangeService::class.java)
        return ApiClient(gitHubService)
    }

    @Singleton
    @Provides
    fun getGsonInstance(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }
}