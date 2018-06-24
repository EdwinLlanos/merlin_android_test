package com.merlinjobs.currencyexchange

import android.content.Context
import android.test.mock.MockContext
import com.merlinjobs.currencyexchange.common.AndroidTest
import com.merlinjobs.currencyexchange.common.mock
import com.merlinjobs.currencyexchange.core.use_cases.base.ICompletableUseCase
import com.merlinjobs.currencyexchange.core.use_cases.base.ISingleUseCase
import com.merlinjobs.currencyexchange.data.local.models.Currency
import com.merlinjobs.currencyexchange.splash.mvvm.ui.SplashScreenViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock


class SplashScreenViewModelTest : AndroidTest() {

    val mGetCurrenciesUseCase: ISingleUseCase<List<Currency>, Context> = mock()
    val mGetExchangeRateUseCase: ICompletableUseCase<Pair<String, String>> = mock()
    val mCreateLocalStorageUseCase: ICompletableUseCase<Context> = mock()
    val mGetFavoriteCurrenciesUseCase: ISingleUseCase<List<String>, Any?> = mock()


    private var viewModel: SplashScreenViewModel? = null
    private var context = mock(MockContext::class.java)

    private var compositeDisposable: CompositeDisposable? = null

    @Before
    fun setUp() {
        compositeDisposable = CompositeDisposable()
        val testScheduler = TestScheduler()
        viewModel = SplashScreenViewModel(mGetCurrenciesUseCase, mGetExchangeRateUseCase, mCreateLocalStorageUseCase, mGetFavoriteCurrenciesUseCase, compositeDisposable!!, context)
    }


    @Test
    fun `should loadData success`() {
        Mockito.`when`(viewModel!!.loadData())
                .then {
                    mGetExchangeRateUseCase.execute(Pair("USD", ""), disposable)
                }


    }

    val disposable = object : DisposableCompletableObserver() {
        override fun onComplete() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onError(e: Throwable) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}