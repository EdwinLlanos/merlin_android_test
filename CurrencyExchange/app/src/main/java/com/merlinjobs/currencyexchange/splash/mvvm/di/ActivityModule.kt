package com.merlinjobs.currencyexchange.splash.mvvm.di

import com.merlinjobs.currencyexchange.splash.mvvm.ui.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashScreenActivity(): SplashScreenActivity
}