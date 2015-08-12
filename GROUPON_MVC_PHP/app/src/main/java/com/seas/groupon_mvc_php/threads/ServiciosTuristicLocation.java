package com.seas.groupon_mvc_php.threads;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.seas.groupon_mvc_php.MapaActivity;
import com.seas.groupon_mvc_php.R;
import com.seas.groupon_mvc_php.beans.Oferta;
import com.seas.groupon_mvc_php.datos.GrouponData;
import com.seas.groupon_mvc_php.tools.Post;
import com.seas.groupon_mvc_php.tools.Utils;


public class ServiciosTuristicLocation {
	final Handler handleTouristic = new Handler();
	private JSONArray datos; 
	
	private OverlayItem getOverlayItem( String nombre, String descripcion, String latitud, String longitud, Drawable drawable){
		 	String coordinates[] = {latitud,longitud};
	   		double lat = Double.parseDouble(coordinates[0]);
	   		double lng = Double.parseDouble(coordinates[1]);
	   		GeoPoint point = new GeoPoint((int) (lng * 1E6),(int) (lat * 1E6));
	   		OverlayItem overlayitem = new OverlayItem(point, nombre, descripcion);
	   		overlayitem.setMarker(MapaActivity.getInstance().getItemizedoverlay().boundCenterBottomAux(drawable));
	   		return overlayitem;
	 }
	
	public void miThread(final String idc , final String idsc){
			Thread t = new Thread(){
				public void run(){
					Post post = null;
					 try{
					 	 ArrayList<String> parametros = new ArrayList<String>();
						 if(!(idc ==null || idc.isEmpty () || idc.trim().equals(""))){
							 parametros.add("idc");
							 parametros.add(idc);
						 }

						 if(!(idsc ==null || idsc.isEmpty () || idsc.trim().equals(""))){
							 parametros.add("idsc");
							 parametros.add(idsc);
						 }
						 //Llamada a Servidor Web PHP
						 post = new Post();
						 // datos = post.getServerData(parametros,"http://seasmfm.hostzi.com/ofertas.php");
						 datos = post.getServerData(parametros,"http://yensai.es/groupon/ofertas.php");
					} catch (Exception e) {
						Toast.makeText(
								MapaActivity.getInstance().getBaseContext(),
								e.getMessage(), Toast.LENGTH_SHORT).show();
					}
					 handleTouristic.post(procesoTouristic);
				}
			};
			t.start();
	}
	 
		final Runnable procesoTouristic = new Runnable(){
			public void run(){
				try {
					ImageView imagenView = new ImageView(MapaActivity.getInstance().getBaseContext());
					if(datos!=null && datos.length()>0){
						//ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
						for(int i = 0 ; i <datos.length() ; i++){
							JSONObject json_data = datos.getJSONObject(i);
							int idOferta = json_data.getInt("id_oferta");
							String tituloOferta = json_data.getString("titulo_oferta");
							String descripcionOferta = json_data.getString("descripcion_oferta");
							String imgOferta = json_data.getString("imagen_oferta");
							String longCentro = json_data.getString("long_centro");
							String latCentro = json_data.getString("lat_centro");
							String urlImagen = "http://yensai.es/groupon/images/" + imgOferta;
							Bitmap loadedImage = downloadFile(urlImagen);
							imagenView.setImageBitmap(loadedImage);
							Utils utils = new Utils(urlImagen);
							utils.start();
							while(utils.isEjecucion()){
								// Me quedo a la espera del segundo hilo.
							}
							imagenView.setImageBitmap(utils.getLoadedImage()); 
							OverlayItem overlay = getOverlayItem(tituloOferta,descripcionOferta,latCentro,longCentro,imagenView.getDrawable());
							MapaActivity.getInstance().anadePuntoAlMapaConItemizedOverlayEImagen(overlay);
							//mapOverlays.add(overlay);
							//GrouponData.setLstOverlays(mapOverlays);
						}
					}
					MapaActivity.getInstance().anadePuntoAlMapaConOverlay("-16.522773","-68.111826");
				}catch (Exception e) {
					Toast.makeText(MapaActivity.getInstance().getBaseContext(),
							e.getMessage(),
							Toast.LENGTH_SHORT).show();
				}
			}
		};
		
		public Bitmap downloadFile(String imageHttpAddress) {
	        URL imageUrl = null;
	        Bitmap loadedImage = null;
	        HttpURLConnection conn = null; 
	        try {
	            imageUrl = new URL(imageHttpAddress);
	            conn = (HttpURLConnection) imageUrl.openConnection();
	            conn.connect();
	            loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
	        } catch (IOException e) {
	        }finally{
	        	if(conn!=null){conn.disconnect();}
	        }
	        return loadedImage;
	    }
}
