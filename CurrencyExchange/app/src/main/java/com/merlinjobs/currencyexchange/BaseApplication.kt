package com.merlinjobs.currencyexchange

import android.app.Activity
import android.app.Application
import com.merlinjobs.currencyexchange.data.local.models.Currency
import com.merlinjobs.currencyexchange.di.DaggerUseCaseComponent
import com.merlinjobs.currencyexchange.di.UseCaseComponent
import com.merlinjobs.currencyexchange.di.UseCaseModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BaseApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
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
        useCaseComponent.inject(this)
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
