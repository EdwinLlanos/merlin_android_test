package com.merlinjobs.currencyexchange.splash.mvvm.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.merlinjobs.currencyexchange.BaseApplication
import com.merlinjobs.currencyexchange.core.use_cases.base.ICompletableUseCase
import com.merlinjobs.currencyexchange.core.use_cases.base.ISingleUseCase
import com.merlinjobs.currencyexchange.data.local.models.Currency
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class SplashScreenViewModel
@Inject constructor(private val mGetCurrenciesUseCase: ISingleUseCase<List<Currency>, Context>,
                    private val mGetExchangeRateUseCase: ICompletableUseCase<Pair<String, String>>,
                    private val mCreateLocalStorageUseCase: ICompletableUseCase<Context>,
                    private val mGetFavoriteCurrenciesUseCase: ISingleUseCase<List<String>, Any?>)
    : ViewModel() {

    private val context: Context = BaseApplication.instance!!.applicationContext
    private val mDisposableBag = CompositeDisposable()
    val stateLiveData = MutableLiveData<SplashState>()


    fun loadData() {
        val disposable = object : DisposableSingleObserver<List<Currency>>() {
            override fun onSuccess(t: List<Currency>) {
                BaseApplication.instance!!.setmCurrencies(t)
                createLocalStorage()
                mDisposableBag.remove(this)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mDisposableBag.remove(this)
                stateLiveData.value = DoneState(false, e.message)
            }
        }
        mDisposableBag.add(disposable)
        mGetCurrenciesUseCase.execute(context, disposable)

    }

    private fun createLocalStorage() {
        val disposable = object : DisposableCompletableObserver() {
            override fun onComplete() {
                getFavoriteCurrencies()
                mDisposableBag.remove(this)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mDisposableBag.remove(this)
                stateLiveData.value = DoneState(false, e.message)
            }
        }

        mDisposableBag.add(disposable)
        mCreateLocalStorageUseCase.execute(context, disposable)


    }

    private fun getFavoriteCurrencies() {
        val disposable = object : DisposableSingleObserver<List<String>>() {
            override fun onSuccess(t: List<String>) {
                getExchangeRates(t)
                mDisposableBag.remove(this)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                mDisposableBag.remove(this)
                stateLiveData.value = DoneState(false, e.message)
            }
        }
        mDisposableBag.add(disposable)
        mGetFavoriteCurrenciesUseCase.execute(null, disposable)
    }

    private fun getExchangeRates(currencies: List<String>) {
        val disposable = object : DisposableCompletableObserver() {
            override fun onComplete() {
                //  mView?.navigateToNextActivity()
                stateLiveData.value = DoneState(true, null)
                mDisposableBag.remove(this)
            }

            override fun onError(e: Throwable) {
                mDisposableBag.remove(this)
                e.printStackTrace()
                stateLiveData.value = DoneState(false, e.message)
            }
        }
        mDisposableBag.add(disposable)
        mGetExchangeRateUseCase.execute(Pair("USD", currencies.toString()
                .replace("[", "")
                .replace("]", "")), disposable)
    }

    override fun onCleared() {
        super.onCleared()
        mDisposableBag.clear()
    }
}


