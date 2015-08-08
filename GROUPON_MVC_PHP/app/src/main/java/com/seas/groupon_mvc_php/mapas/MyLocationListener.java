package com.seas.groupon_mvc_php.mapas;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import com.seas.groupon_mvc_php.MapaActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocationListener implements LocationListener 
{
	public void onLocationChanged(Location loc) {
	try{
		String longitud;
		String latitud;
		if (loc != null) {
        	longitud = String.valueOf(loc.getLongitude());
        	latitud= String.valueOf(loc.getLatitude());
            Toast.makeText(MapaActivity.getInstance().getBaseContext(), 
                "Location changed : Lat: " + loc.getLatitude() + 
                " Lng: " + loc.getLongitude(), 
                Toast.LENGTH_SHORT).show();
            MapaActivity.getInstance().anadePuntoAlMapaConOverlay(latitud,longitud);
			//overlayItemAnterior = overlay;
        }
	}catch(Exception e){
		  Toast.makeText(MapaActivity.getInstance().getBaseContext(), 
	                "Error al cargar la Geolocalizacion. ",
	                Toast.LENGTH_SHORT).show();
	}
    }
	private OverlayItem overlayItemAnterior = null;
	private OverlayItem getOverlayItem( String nombre, String descripcion, String latitud, String longitud, Drawable drawable){
	 	String coordinates[] = {latitud,longitud};
   		double lat = Double.parseDouble(coordinates[0]);
   		double lng = Double.parseDouble(coordinates[1]);
   		GeoPoint point = new GeoPoint((int) (lat * 1E6),(int) (lng * 1E6));
   		OverlayItem overlayitem = new OverlayItem(point, nombre, descripcion);
   		overlayitem.setMarker(MapaActivity.getInstance().getItemizedoverlay().boundCenterBottomAux(drawable));
   		return overlayitem;
 }

	public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
		 Intent intent = new Intent( android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		 MapaActivity.getInstance().startActivity(intent);
    }
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }
    public void onStatusChanged(String provider, int status, 
        Bundle extras) {
        // TODO Auto-generated method stub
    }
}