package com.merlinjobs.currencyexchange.splash.mvvm.di

import com.merlinjobs.currencyexchange.splash.mvvm.net.NetworkModule
import dagger.Module

@Module(includes = [
    ViewModelModule::class,
    NetworkModule::class
])
class AppModule