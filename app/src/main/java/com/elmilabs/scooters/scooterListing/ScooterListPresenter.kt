package com.elmilabs.scooters.scooterListing

import com.elmilabs.scooters.AbstractPresenter
import com.elmilabs.scooters.scooterListing.model.repository.ScooterRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ScooterListPresenter @Inject constructor() :
    AbstractPresenter<ScooterListContract.View, ScooterListContract.Presenter>(),
    ScooterListContract.Presenter {
    @Inject
    lateinit var scooterRepository: ScooterRepository
    lateinit var subscribe: Disposable


    override fun getListOfScooters() {

        launch {
            subscribe = Observable.interval(0, 10, TimeUnit.SECONDS).subscribe(fun(_: Long) {
                scooterRepository.getScooterList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        view.showLoading(false)
                        throw RuntimeException("hey")
                    }
                    .switchMap { scootersResponse ->
                        return@switchMap Observable.just(scootersResponse.data.scooters.sortedWith(compareBy { it.distance_to_travel }))
                    }.subscribe({
                        view.showLoading(true)
                        view.displayData(it)

                    }, {
                        view.showLoading(false)

                    }, {
                        //                            view.showLoading(false)
                    }
                    )
            },
                {
                    view.showLoading(false)
                    error(it.localizedMessage)
                }


            )
            subscribe
        }

    }


}