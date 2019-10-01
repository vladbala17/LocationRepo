package com.android.vlad.locations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.vlad.data.model.Location
import com.android.vlad.mylocations.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.location_item.view.*
import kotlin.properties.Delegates

class LocationAdapter(private val onLocationClicked: (imageUrl: String) -> Unit) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private var locationList: List<Location> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)

        val holder = LocationViewHolder(view)

        holder.itemView.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                onLocationClicked.invoke(locationList[holder.position].imageUrl)
            }
        }
        return holder
    }

    override fun getItemCount(): Int = locationList.size


    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val location = locationList[position]
            ViewCompat.setTransitionName(holder.itemView.location_image, location.imageUrl)
            holder.bind(location)
        }
    }

    fun updateData(newLocationList: List<Location>) {
        locationList = newLocationList
    }

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(location: Location) {
            Glide.with(itemView).load(location.imageUrl).centerCrop().into(itemView.location_image)
            itemView.address_text.text = location.address
            itemView.tag_text.text = location.tag
        }

    }
}