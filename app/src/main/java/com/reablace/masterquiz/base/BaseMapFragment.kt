package com.reablace.masterquiz.base

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.reablace.masterquiz.common.MY_PERMISSIONS_REQUEST_GOOGLE_MAP

open class BaseMapFragment : BaseFragment() {

    var mMap: GoogleMap? = null


    override fun onStart() {
        super.onStart()
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(
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

    fun showRationaleAndRequest() {
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
}