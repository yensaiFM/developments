package com.seas.groupon_mvc_php;

import java.util.List;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.seas.groupon_mvc_php.mapas.MiItemizedOverlay;
import com.seas.groupon_mvc_php.mapas.MiOverlay;
import com.seas.groupon_mvc_php.mapas.MyLocationListener;
import com.seas.groupon_mvc_php.threads.ServiciosTuristicLocation;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class MapaActivity extends MapActivity {
	
	private LocationManager lm = null;
    private MyLocationListener locationListener = null;
    private String best = null;

    private MapView mapView = null;
    private MapController mc = null;
    private List<Overlay> mapOverlays = null;
    
    private MiItemizedOverlay itemizedoverlay = null;
    public MiItemizedOverlay getItemizedoverlay() {
		return itemizedoverlay;
	}

	public void setItemizedoverlay(MiItemizedOverlay itemizedoverlay) {
		this.itemizedoverlay = itemizedoverlay;
	}
	private ServiciosTuristicLocation serviciosTuristicLocation = null;

    
    private static MapaActivity mapaActivity = null;
    public static MapaActivity getInstance(){
    	return mapaActivity;
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa);
		mapaActivity = this;

    	serviciosTuristicLocation = new ServiciosTuristicLocation();
    	
        mapView = (MapView) findViewById(R.id.mapView);
	    mc = mapView.getController();
	    mapView.setBuiltInZoomControls(true);
	    mapView.setTraffic(true);
	    itemizedoverlay = new MiItemizedOverlay(this);
	    serviciosTuristicLocation.miThread("", "");
	    /////////////////////////////////////////////////////////////////////////////////
	    ////////////////////GEOLOCALIZARME
	    ////////////////////////////////////////////////////////////////////////////////
	    //registraServiciosDeLocalizacionGPS();
	    mc.setZoom(12);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_mapa, menu);
		return true;
	}
	public synchronized void anadePuntoAlMapaConItemizedOverlayEImagen(OverlayItem overlayItem) throws Exception{
		mapOverlays = mapView.getOverlays();
		itemizedoverlay.addOverlay(overlayItem);
   		mapOverlays.add(itemizedoverlay);
   		mc.animateTo(overlayItem.getPoint());
	    mapView.invalidate();
 }
	public synchronized void anadePuntoAlMapaConOverlay(String latitud, String longitud){
	 	   String coordinates[] = {latitud,longitud};
	 	   double lat = Double.parseDouble(coordinates[0]);
	       double lng = Double.parseDouble(coordinates[1]);
	       GeoPoint p;
	        p = new GeoPoint(
	                (int) (lat * 1E6), // Trabaja en microgrados para las coordenadas y por eso hay que multiplicar por un millon
	                (int) (lng * 1E6));
	        mapOverlays = mapView.getOverlays();
	        mapOverlays.clear();
	        MiOverlay marker = new MiOverlay(p);
	    	mapOverlays.add(itemizedoverlay);
	    	mapOverlays.add(marker);
	    	mc.animateTo(p);
	        mc.setZoom(14);
	    	mapView.invalidate();
	    }

public synchronized void anadePuntoAlMapaConItemizedOverlayEImagenLocation(OverlayItem overlayItem) throws Exception{
		mapOverlays = mapView.getOverlays();
		itemizedoverlay.addOverlay(overlayItem);
   		mapOverlays.add(itemizedoverlay);
   		mc.animateTo(overlayItem.getPoint());
	    mapView.invalidate();	
}
private void registraServiciosDeLocalizacionGPS(){
    lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

    Criteria criteria = new Criteria();
    best = lm.getBestProvider(criteria, true);

    Location location = lm.getLastKnownLocation(best);
    if(location!=null){
    }
    locationListener = new MyLocationListener();
    lm.requestLocationUpdates(
    		best,  
            4000,
            0,
            locationListener); 
}

@Override
protected boolean isRouteDisplayed() {
	// TODO Auto-generated method stub
	return false;
}

}
