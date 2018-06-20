package com.merlinjobs.currencyexchange.di

import com.merlinjobs.currencyexchange.BaseApplication
import com.merlinjobs.currencyexchange.exchange.ExchangeActivityPresenter
import com.merlinjobs.currencyexchange.preferences.PreferenceFragmentDialogPresenter
import com.merlinjobs.currencyexchange.splash.SplashScreenPresenter
import com.merlinjobs.currencyexchange.splash.mvvm.di.ActivityModule
import com.merlinjobs.currencyexchange.splash.mvvm.di.AppModule
import com.merlinjobs.currencyexchange.splash.mvvm.ui.SplashScreenViewModel
import com.merlinjobs.currencyexchange.splash.mvvm.di.ViewModelFactoryModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(UseCaseModule::class), AppModule::class, AndroidSupportInjectionModule::class, ViewModelFactoryModule::class, ActivityModule::class])
interface UseCaseComponent {

    fun inject(splashScreenPresenter: SplashScreenPresenter)

    fun inject(splashScreenViewModel: SplashScreenViewModel)

    fun inject(exchangeActivityPresenter: ExchangeActivityPresenter)

    fun inject(preferenceDialogPresenter: PreferenceFragmentDialogPresenter)

    fun inject(app: BaseApplication)

}