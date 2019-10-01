package com.android.vlad.location

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.vlad.loadImage
import com.android.vlad.mylocations.R
import kotlinx.android.synthetic.main.activity_location_detail.*

const val EXTRA_LOCATION_IMAGE_URL = "EXTRA_LOCATION_IMAGE_URL"

class LocationDetail : AppCompatActivity() {
    companion object {
        fun getStartIntent(context: Context, imageUrl: String): Intent {
            return Intent(context, LocationDetail::class.java)
                .putExtra(EXTRA_LOCATION_IMAGE_URL, imageUrl)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        val imageUrl = intent.getStringExtra(EXTRA_LOCATION_IMAGE_URL)

        detailLocationImage.loadImage(imageUrl)
    }
}
