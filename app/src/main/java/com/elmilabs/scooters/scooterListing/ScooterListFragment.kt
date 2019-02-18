package com.elmilabs.scooters.scooterListing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.elmilabs.scooters.ScooterApplication
import com.elmilabs.scooters.scooterListing.model.entities.Scooter
import com.elmilabs.scooters.di.DaggerFragmentComponent
import kotlinx.android.synthetic.main.fragment_scooter_list.*
import javax.inject.Inject

class ScooterListFragment : Fragment(), ScooterListContract.View {
    @Inject
    override lateinit var presenter: ScooterListPresenter
    private val scooterListAdapter = ScooterListAdapter { scooter ->

        println(scooter)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (activity?.application as ScooterApplication).graph.inject(this)

        DaggerFragmentComponent.create().inject(this)
        presenter.view = this


        return inflater.inflate(com.elmilabs.scooters.R.layout.fragment_scooter_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        presenter.getListOfScooters()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPause() {
        presenter.detachPresenter()
        super.onPause()
    }

    override fun showLoading(showLoading: Boolean) {
        println(showLoading)
    }

    override fun displayData(data: List<Scooter>) {
        scooterListAdapter.addItems(data)
    }

    override fun showError() {
        println("error")
    }


    private fun setupView() {
        scooterRecView.layoutManager = LinearLayoutManager(context)
        scooterRecView.apply {
            adapter = scooterListAdapter
        }
    }

}
