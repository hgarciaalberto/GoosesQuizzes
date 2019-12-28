package com.reablace.masterquiz.ui.listevent

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.reablace.masterquiz.R
import com.reablace.masterquiz.base.BaseFragment
import com.reablace.masterquiz.common.MY_PERMISSIONS_REQUEST_GOOGLE_MAP
import com.reablace.masterquiz.common.ViewModelFactory
import com.reablace.masterquiz.models.QuizEvent
import javax.inject.Inject

private const val TAG = "EventMapFragment"

class EventMapFragment : BaseFragment(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mapViewModel: MapViewModel

    private var mMap: GoogleMap? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getControllerComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapViewModel = ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Events observer
        mapViewModel.events.observe(viewLifecycleOwner, Observer { setMarkers(it) })

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (checkSelfPermission(
                activity!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap?.isMyLocationEnabled = true

            mapViewModel.fetchEventList()

            val edinburgh = LatLng(55.953472, -3.188275)
            val cameraPosition = CameraPosition.builder()
                .target(edinburgh)
                .zoom(13f)
                .build()
            mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

            mMap?.setOnMarkerClickListener {
                mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(it.position, 6f))
                false
            }

        } else {
            // Show rationale and request permission.
            showRationaleAndRequest()
        }
    }

    override fun onStart() {
        super.onStart()
        // Here, thisActivity is the current activity
        if (checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) { //ACCESS_COARSE_LOCATION

            // Permission is not granted
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                showRationaleAndRequest()
            } else {
                // No explanation needed, we can request the permission.
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_GOOGLE_MAP)

                // MY_PERMISSIONS_REQUEST_GOOGLE_MAP is an app-defined int constant.
                // The callback method gets the result of the request.
            }
        } else {
            // Permission has already been granted
            Toast.makeText(activity, "Permissions already granted", Toast.LENGTH_LONG).show()
        }
    }

    private fun showRationaleAndRequest() {
        Toast.makeText(activity, "Show an explantation", Toast.LENGTH_LONG).show() //TODO: show explanation
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_GOOGLE_MAP)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_GOOGLE_MAP -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the contacts-related task you need to do.
                    Toast.makeText(requireActivity(), "Permissions was granted", Toast.LENGTH_LONG).show()
                } else {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                    Toast.makeText(requireActivity(), "Permissions was denied", Toast.LENGTH_LONG).show()
                    fragmentManager?.popBackStack()
                }
                return
            }

            // Add other 'when' lines to check for other permissions this app might request.
            else -> {
                // Ignore all other requests.
                Toast.makeText(requireActivity(), "Ignore all other requests", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Update map markers when
     */
    private fun setMarkers(events: List<QuizEvent>) {

        if (mMap == null) {
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }

        mMap?.clear()

        events.forEach {

            val location = it.location?.let { geoPoint ->
                LatLng(geoPoint.latitude, geoPoint.longitude)
            }

            location?.apply {
                mMap?.addMarker(
                    MarkerOptions()
                        .position(this)
                        .title(it.name)
                        .snippet(it.state)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)))
            }
        }
    }
}

// https://developers.google.com/maps/documentation/android-sdk/location