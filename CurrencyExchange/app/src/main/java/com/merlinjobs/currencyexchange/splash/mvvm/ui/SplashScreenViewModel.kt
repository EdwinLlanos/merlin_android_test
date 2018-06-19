package com.merlinjobs.currencyexchange.splash.mvvm.ui

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

interface MainVM {
    val showData: Observable<Unit>
    val progress: Observable<Boolean>
    val errors: Observable<Int>
}

interface LoadingVM {
    fun getRepo(owner: String, repo: String)
}
class SplashScreenViewModel : ViewModel(), MainVM, LoadingVM {

    override val progress: PublishSubject<Boolean> = PublishSubject.create()
    override val errors: PublishSubject<Int> = PublishSubject.create()
    override val showData: PublishSubject<Unit> = PublishSubject.create()

    private var getRepoDisposable: Disposable? = null

    override fun getRepo(owner: String, repo: String) {
        getRepoDisposable?.dispose()
    }

    override fun onCleared() {
        getRepoDisposable?.dispose()
    }

}