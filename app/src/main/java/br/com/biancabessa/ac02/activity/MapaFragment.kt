package br.com.biancabessa.ac02.activity

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.biancabessa.ac02.R
import br.com.biancabessa.ac02.`object`.PermissionUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapaFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,

                              savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_mapa, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment)

                as SupportMapFragment

        mapFragment.getMapAsync(this)
        return view
    }

    private var map: GoogleMap? = null
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        this.map = googleMap

        val ok = PermissionUtils.validate(activity!!, 1,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        if (ok) map?.isMyLocationEnabled = true

        val location = LatLng(-23.5593356, -46.4285095)
        val update = CameraUpdateFactory.newLatLngZoom(location, 18f)
        map?.moveCamera(update)
        map?.addMarker(
            MarkerOptions()

            .title("MB")
            .snippet("Mercearia Bessa")
            .position(location)

        )
        map?.mapType=GoogleMap.MAP_TYPE_NORMAL
    }
}