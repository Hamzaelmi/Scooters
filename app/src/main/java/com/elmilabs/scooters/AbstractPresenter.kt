package com.elmilabs.scooters

import androidx.annotation.CallSuper
import com.elmilabs.scooters.base.BasePresenter
import com.elmilabs.scooters.base.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbstractPresenter<V : BaseView<P>, out P : BasePresenter<V>> : BasePresenter<V> {

    override lateinit var view: V

    private val disposables = CompositeDisposable()

    fun launch(job: () -> Disposable) {
        disposables.add(job())
    }

    @CallSuper
    override fun stop() {
        disposables.clear()
    }

    @CallSuper
    open fun detachPresenter() = stop()


    open fun onViewDetached() {}


}