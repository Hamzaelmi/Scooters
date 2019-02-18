package com.elmilabs.scooters.scooterListing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elmilabs.scooters.R
import com.elmilabs.scooters.scooterListing.model.entities.Scooter
import kotlinx.android.synthetic.main.item_list_scooter.view.*

class ScooterListAdapter(private val onItemClick: (scooter: Scooter) -> Unit) :
    RecyclerView.Adapter<ScooterListAdapter.ScooterHolder>() {

    private var scooterList = ArrayList<Scooter>()

    fun addItems(list: List<Scooter>) {
        scooterList= ArrayList(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScooterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_list_scooter, parent, false)
        return ScooterHolder(view)
    }

    override fun getItemCount(): Int = scooterList.size

    override fun onBindViewHolder(holder: ScooterHolder, position: Int) {
        val scooter = scooterList[position]
        holder.bind(scooter)
        holder.itemView.setOnClickListener {
            onItemClick(scooter)
        }
    }

    inner class ScooterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(scooter: Scooter) = with(itemView) {

            scooterBattery.text =( scooter.energy_level.toString())
            scooterLicencePlate.text =( scooter.distance_to_travel.toString())
            scooterDistance.text = scooter.location.lat.toString()
        }
    }
}