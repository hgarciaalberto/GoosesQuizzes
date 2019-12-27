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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.QuerySnapshot
import com.reablace.masterquiz.R
import com.reablace.masterquiz.base.BaseFragment
import com.reablace.masterquiz.common.*
import com.reablace.masterquiz.firebase.firestore.FirestoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val TAG = "EventMapFragment"

class EventMapFragment : BaseFragment(), OnMapReadyCallback, CoroutineScope {

    @Inject
    lateinit var firestoreRepository: FirestoreRepository

    private lateinit var mMap: GoogleMap


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

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

        if (checkSelfPermission(
                activity!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true

            getEventMarkers()

            val edinburgh = LatLng(55.953472, -3.188275)
            val cameraPosition = CameraPosition.builder()
                .target(edinburgh)
                .zoom(13f)
                .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

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
        Toast.makeText(activity, "Show an explantation", Toast.LENGTH_LONG).show()
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

    private fun getEventMarkers() {
        val uiScope = CoroutineScope(Dispatchers.Main)
        uiScope.launch {
            setMarkers(firestoreRepository.getFilterEventList(FUTURE_EVENTS))
        }
    }

    private fun setMarkers(events: QuerySnapshot) {


        events.forEach {

            val location = it.getGeoPoint(EVENTS_FIELD_LOCATION)?.let { geoPoint ->
                LatLng(geoPoint.latitude, geoPoint.longitude)
            }

            mMap.addMarker(
                MarkerOptions()
                    .position(location!!)
                    .title(it.getString(EVENTS_FIELD_NAME))
                    .snippet(it.getString(EVENTS_FIELD_STATE)))
        }
    }
}


// https://developers.google.com/maps/documentation/android-sdk/location