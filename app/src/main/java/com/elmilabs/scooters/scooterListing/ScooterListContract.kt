package com.elmilabs.scooters.scooterListing

import com.elmilabs.scooters.base.BasePresenter
import com.elmilabs.scooters.base.BaseView
import com.elmilabs.scooters.scooterListing.model.entities.Scooter

interface ScooterListContract {

    interface View : BaseView<Presenter> {
        fun showLoading(showLoading: Boolean)
        fun displayData(data: List<Scooter>)
        fun showError()

    }

    interface Presenter : BasePresenter<View> {
        fun getListOfScooters()
    }
}