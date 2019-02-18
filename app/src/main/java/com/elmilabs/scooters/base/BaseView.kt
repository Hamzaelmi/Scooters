package com.elmilabs.scooters.base

interface BaseView<out T : BasePresenter<*>> {

    val presenter: T

}