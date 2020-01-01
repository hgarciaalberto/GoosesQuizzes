package com.reablace.masterquiz.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.reablace.masterquiz.R
import com.reablace.masterquiz.base.BaseMapFragment
import com.reablace.masterquiz.common.EVENT
import com.reablace.masterquiz.models.QuizEvent

class EventDetailsMapFragment : BaseMapFragment(), OnMapReadyCallback {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getControllerComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ContextCompat.checkSelfPermission(
                activity!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap?.isMyLocationEnabled = true

            getMarker()

            mMap?.setOnMarkerClickListener {
                mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(it.position, 6f))
                false
            }

        } else {
            // Show rationale and request permission.
            super.showRationaleAndRequest()
        }
    }

    private fun getMarker() {
        val event = arguments?.getSerializable(EVENT) as QuizEvent

        val location = event.location?.let { geoPoint ->
            LatLng(geoPoint.latitude, geoPoint.longitude)
        }
        setMarkers(location)
    }

    /**
     * Update map markers when
     */
    private fun setMarkers(markerLocation: LatLng?) {

        if (mMap == null) {
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }

        mMap?.clear()

        // Add marker
        markerLocation?.apply {
            mMap?.addMarker(
                MarkerOptions()
                    .position(this)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)))
        }

        // Apply zoom to maker
        val cameraPosition = CameraPosition.builder()
            .target(markerLocation)
            .zoom(15f)
            .build()
        mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }

    companion object {

        private const val TAG = "EventDetailsMapFragment"
        fun newInstance(event: QuizEvent) = EventDetailsMapFragment().apply {
            arguments = Bundle().apply {
                putSerializable(EVENT, event)
            }
        }
    }
}

