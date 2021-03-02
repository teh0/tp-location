package com.champion.theo.tp_permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.champion.theo.tp_permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /**
     * View binding
     */
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var permissionHasBeenAsked: Boolean = false
    private val PERMISSION_REQUEST_LOCATION = 42



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            // Request for camera permission.
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted
                startLocation()
            } else {
                // Permission request was denied.

            }
        }
    }

    private fun requestLocationPermission() {
        // Permission has not been granted and must be requested.
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with a button to request the missing permission.
            //requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_LOCATION)

        } else {

            // Request the permission. The result will be received in onRequestPermissionResult().
            requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_LOCATION)
        }
    }

    private fun initText() {
        if (!permissionHasBeenAsked) {
            binding.authText.text = R.string.access_auth
        }
    }

    private fun startLocation() {

    }
}