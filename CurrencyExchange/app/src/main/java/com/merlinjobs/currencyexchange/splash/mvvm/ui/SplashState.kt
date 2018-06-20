package com.merlinjobs.currencyexchange.splash.mvvm.ui

sealed class SplashState {
    abstract val doneState: Boolean
    abstract val errorMessage: String?
}

data class DoneState(override val doneState: Boolean, override val errorMessage: String?) : SplashState()