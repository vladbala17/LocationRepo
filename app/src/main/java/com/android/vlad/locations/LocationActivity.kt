package com.android.vlad.locations

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.vlad.data.model.Location
import com.android.vlad.location.LocationDetail
import com.android.vlad.mylocations.R
import kotlinx.android.synthetic.main.activity_location.*
import org.koin.android.viewmodel.ext.android.viewModel

class LocationActivity : AppCompatActivity() {

    private val viewModel: LocationsViewModel by viewModel()
    private lateinit var locationAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val onLocationClicked: (imageUrl: String) -> Unit = { imageUrl ->
            viewModel.locationClicked(imageUrl)
        }

        locationAdapter = LocationAdapter(onLocationClicked)

        locationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@LocationActivity)
            adapter = locationAdapter
        }

        initViewModel()
        viewModel.loadLocations()
    }

    private fun initViewModel() {
        viewModel.locations.observe(this, Observer { newLocationList ->
            locationAdapter.updateData(newLocationList)
        })

        viewModel.showLoading.observe(
            this,
            Observer { showLoading ->
                mainProgressBar.visibility = if (showLoading!!) View.VISIBLE else View.GONE
            })

        viewModel.showError.observe(
            this,
            Observer { showError -> Toast.makeText(this, showError, Toast.LENGTH_LONG).show() })

        viewModel.navigateToDetail.observe(this, Observer { imageUrl ->
            if (imageUrl != null) startActivity(LocationDetail.getStartIntent(this, imageUrl))
        })
    }
}
