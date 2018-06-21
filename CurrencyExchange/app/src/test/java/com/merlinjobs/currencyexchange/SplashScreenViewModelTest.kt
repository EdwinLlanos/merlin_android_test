package com.merlinjobs.currencyexchange

import android.test.mock.MockContext
import com.merlinjobs.currencyexchange.core.use_cases.currency.GetCurrenciesUsecase
import com.merlinjobs.currencyexchange.core.use_cases.exchange.GetExchangeRatesUseCase
import com.merlinjobs.currencyexchange.core.use_cases.preferences.GetFavoriteCurrenciesUseCase
import com.merlinjobs.currencyexchange.core.use_cases.system.CreateLocalStorageUseCase
import com.merlinjobs.currencyexchange.splash.mvvm.ui.SplashScreenViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock


class SplashScreenViewModelTest {
    private var mGetCurrenciesUseCase: GetCurrenciesUsecase? = null
    private var mGetExchangeRateUseCase: GetExchangeRatesUseCase? = null
    private var mCreateLocalStorageUseCase: CreateLocalStorageUseCase? = null
    private var mGetFavoriteCurrenciesUseCase: GetFavoriteCurrenciesUseCase? = null
    private var viewModel: SplashScreenViewModel? = null
    private var context = mock(MockContext::class.java)

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        val testScheduler = TestScheduler()
        mGetCurrenciesUseCase = GetCurrenciesUsecase(testScheduler, testScheduler)
        mGetExchangeRateUseCase = GetExchangeRatesUseCase(testScheduler, testScheduler)
        mCreateLocalStorageUseCase = CreateLocalStorageUseCase(testScheduler, testScheduler)
        mGetFavoriteCurrenciesUseCase = GetFavoriteCurrenciesUseCase(testScheduler, testScheduler)
        viewModel = SplashScreenViewModel(mGetCurrenciesUseCase!!, mGetExchangeRateUseCase!!, mCreateLocalStorageUseCase!!, mGetFavoriteCurrenciesUseCase!!, compositeDisposable, context)
    }

    @Test
    fun should_loadData_success() {
        viewModel!!.loadData()
    }
}