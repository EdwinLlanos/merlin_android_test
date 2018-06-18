package com.merlinjobs.currencyexchange

import android.app.Application

import com.merlinjobs.currencyexchange.data.local.models.Currency
import com.merlinjobs.currencyexchange.di.DaggerUseCaseComponent
import com.merlinjobs.currencyexchange.di.UseCaseComponent
import com.merlinjobs.currencyexchange.di.UseCaseModule

class BaseApplication : Application() {

    private var mUseCaseComponent: UseCaseComponent? = null

    private var mCurrencies: List<Currency>? = null


    val useCaseComponent: UseCaseComponent
        get() {
            if (mUseCaseComponent == null) {
                mUseCaseComponent = DaggerUseCaseComponent.builder()
                        .useCaseModule(UseCaseModule())
                        .build()
            }
            return mUseCaseComponent!!
        }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getmCurrencies(): List<Currency>? {
        return mCurrencies
    }

    fun setmCurrencies(mCurrencies: List<Currency>) {
        this.mCurrencies = mCurrencies
    }

    companion object {
        var instance: BaseApplication? = null
            private set
    }
}
