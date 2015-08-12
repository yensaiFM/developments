package com.seas.groupon_mvc_php;

import java.util.List;
import java.util.ArrayList;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.MyLocationOverlay;

import com.seas.groupon_mvc_php.datos.GrouponData;
import com.seas.groupon_mvc_php.mapas.MiItemizedOverlay;
import com.seas.groupon_mvc_php.mapas.MiOverlay;
import com.seas.groupon_mvc_php.mapas.MyLocationListener;
import com.seas.groupon_mvc_php.threads.ServiciosTuristicLocation;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

public class MapaActivity extends MapActivity {
    private LocationManager lm = null;
    private MyLocationListener locationListener = null;
    private String best = null;

    public MapView mapView = null;
    private MapController mc = null;
    private List<Overlay> mapOverlays = null;
    private ArrayList<OverlayItem> elemsOverlay = null;
    public ArrayList<OverlayItem> getM_overlay() {
        return elemsOverlay;
    }
    public void setM_overlay(ArrayList<OverlayItem> elemsOverlay) {
        this.elemsOverlay = elemsOverlay;
    }

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
        if(mapaActivity == null){
            mapaActivity = new  MapaActivity();
        }
        return mapaActivity;
    }
    private MyLocationOverlay me=null;
    Bitmap background;
    private static final String TAG = "MapaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        mapaActivity = this;
        serviciosTuristicLocation = new ServiciosTuristicLocation();

        mapView = (MapView)findViewById(R.id.mapView);
        mc = mapView.getController();
        mapView.setBuiltInZoomControls(true);
        mapView.setTraffic(true);
        //mapa.setFocusable(true);
        //mapa.setSatellite(false);
        //mapa.setFocusable(true);
        //elemsOverlay = GrouponData.getLstOverlays();
        //GeoPoint geoPoint = new GeoPoint((int) (37.974718*1E6), (int) (23.733684*1E6));
        //projection = mapa.getProjection();
        //screenPoint = new Point();
        //projection.toPixels(geoPoint, screenPoint);
        //mc = mapa.getController();
        //mc.setCenter(geoPoint);
        //mc.setZoom(1);

        //mc = mapa.getController();
        //mc.setCenter(getPoint(40.76793169992044, -73.98180484771729));
        //mc.setZoom(12);
        //mapa.setBuiltInZoomControls(true);
        //mapa.setSatellite(true);

        itemizedoverlay = new MiItemizedOverlay(this);
        //Bundle bundle=getIntent().getExtras();
        serviciosTuristicLocation.miThread("", "");
        /////////////////////////////////////////////////////////////////////////////////
        ////////////////////GEOLOCALIZARME
        ////////////////////////////////////////////////////////////////////////////////
        //registraServiciosDeLocalizacionGPS();
        mc.setZoom(12);


        //background = BitmapFactory.decodeResource(getResources(), R.mipmap.market);
        //int width = background.getWidth();
        //int height = background.getHeight();
        //Drawable marker=getResources().getDrawable(R.mipmap.market);
        //marker.setBounds(0, 0, width, height);

        //mapa.getOverlays().add(new SitesOverlay(marker));

        //me=new MyLocationOverlay(this, mapa);
        //mapa.getOverlays().add(me);
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

