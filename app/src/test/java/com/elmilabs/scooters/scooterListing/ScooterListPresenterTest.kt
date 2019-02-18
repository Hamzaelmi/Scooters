package com.elmilabs.scooters.scooterListing

import com.elmilabs.RxImmediateSchedulerRule
import com.elmilabs.RxSchedulersOverrideRule
import com.elmilabs.scooters.scooterListing.model.entities.Data
import com.elmilabs.scooters.scooterListing.model.entities.Meta
import com.elmilabs.scooters.scooterListing.model.entities.ScootersResponse
import com.elmilabs.scooters.scooterListing.model.repository.ScooterRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ScooterListPresenterTest {
    companion object {
        @Rule
        @JvmField
        val schedulers = RxSchedulersOverrideRule()
    }

    private lateinit var presenter: ScooterListPresenter
    @Mock
    lateinit var mMockRepository: ScooterRepository
    @Mock
    lateinit var mMockView: ScooterListContract.View


    @Before
    fun setup() {
        this.presenter = ScooterListPresenter()
        presenter.scooterRepository = mMockRepository
        presenter.view = mMockView
        
    }

    @Test
    fun `should show loading and return observable from the repository`() {
        val mockedResponse = ScootersResponse(Meta("", "", 200), Data(arrayListOf()))
        val observable = Observable.just(mockedResponse)
        `when`(mMockRepository.getScooterList()).thenReturn(observable)
        presenter.getListOfScooters()
//        `when`(mMockRepository.getScooterList()).thenThrow(RuntimeException("Error"))

        verify(mMockRepository, times(1)).getScooterList()
        verify(mMockView, times(1)).showLoading(true)
        presenter.detachPresenter()
        presenter.stop()

    }

    @Test
    fun `should hide loading on error`() {


        `when`(mMockRepository.getScooterList()).thenThrow(RuntimeException("Error"))
        presenter.getListOfScooters()
        verify(mMockView).showLoading(false)
        presenter.detachPresenter()
        presenter.stop()

    }


}