package com.champion.theo.tp_permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.champion.theo.tp_permission.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    /**
     * View binding
     */
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    /**
     * Permission boolean
     */
    private var permissionHasBeenAsked: Boolean = false

    /**
     * Permission code
     */
    private val PERMISSION_REQUEST_LOCATION = 42


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        initText()
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
                binding.authText.text = getString(R.string.message_auth)
                requestLocationPermission()
            }
        }
    }

    private fun requestLocationPermission() {
        // Permission has not been granted and must be requested.
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            displayAlertDialog()
        } else {
            askLocationPermission()
        }
    }

    private fun initText() {
        if (!permissionHasBeenAsked) {
            binding.authText.text = getString(R.string.no_ask_message_auth)
        }
    }

    private fun askLocationPermission() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_LOCATION)
    }

    private fun displayAlertDialog() {
        AlertDialog.Builder(baseContext)
            .setTitle("Delete entry")
            .setMessage("Vous devez autoriser la localisation")
            .show()
    }

    private fun startLocation() {
        print("Your position is :::")
    }
}