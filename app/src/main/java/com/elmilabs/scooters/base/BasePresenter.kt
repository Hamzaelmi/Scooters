package com.elmilabs.scooters.base

 interface BasePresenter<T> {

    fun stop()

    var view: T
}
