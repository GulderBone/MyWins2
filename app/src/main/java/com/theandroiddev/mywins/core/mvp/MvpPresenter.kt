package com.theandroiddev.mywins.core.mvp

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.theandroiddev.mywins.utils.Constants
import com.theandroiddev.mywins.utils.Constants.Companion.Category
import com.theandroiddev.mywins.utils.MvpBundle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.io.Serializable


abstract class MvpPresenter<V : MvpView, B : Serializable> : MvpBasePresenter<V>() {

    lateinit var bundle: B

    val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun onViewCreated()

    fun Disposable.addToDisposables(disposables: CompositeDisposable) {
        disposables.add(this)
    }

    override fun detachView() {
        super.detachView()
        disposables.clear()
    }
}
