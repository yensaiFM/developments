package com.seas.groupon_mvc_php.threads;

import android.os.Handler;
import android.widget.Toast;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.seas.groupon_mvc_php.ListaOfertasActivity;
import com.seas.groupon_mvc_php.MapaActivity;
import com.seas.groupon_mvc_php.R;
import com.seas.groupon_mvc_php.beans.Oferta;
import com.seas.groupon_mvc_php.tools.Post;
import com.seas.groupon_mvc_php.tools.Utils;
import com.seas.groupon_mvc_php.datos.GrouponData;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;



public class ServiciosLista {
	final Handler handle = new Handler();
	private static String messageUser;
	private JSONArray datos;
	private static final String TAG = "ServiciosLista";

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
								ListaOfertasActivity.getInstance().getBaseContext(),
								e.getMessage(), Toast.LENGTH_SHORT).show();
					}
					handle.post(proceso);
				}
			};
			t.start();
	}
	 
	final Runnable proceso = new Runnable(){
			public void run(){
				try {
					Log.e(TAG, "Entra proceso servicios lista");
					//ImageView imagenView = new ImageView(MapaActivity.getInstance().getBaseContext());
					if(datos!=null && datos.length()>0){
						ArrayList<Oferta> m_ofertas = new ArrayList<Oferta>();
						for(int i = 0 ; i <datos.length() ; i++){
								Log.e(TAG, "Procesa elem" + i);
								JSONObject json_data = datos.getJSONObject(i);
								int idOferta = json_data.getInt("id_oferta");
								Log.e(TAG, "Elem" + i + " idOferta " + idOferta);
								String tituloOferta = json_data.getString("titulo_oferta");
								Log.e(TAG, "Elem" + i + " tituloOferta " + tituloOferta);
								String descripcionOferta = json_data.getString("descripcion_oferta");
								Log.e(TAG, "Elem" + i + " descripcionOferta " + descripcionOferta);
								String imgOferta = json_data.getString("imagen_oferta");
								Log.e(TAG, "Elem" + i + " imgOferta " + imgOferta);
								String condicionesOferta = json_data.getString("condiciones_oferta");
								Log.e(TAG, "Elem" + i + " condicionesOferta " + condicionesOferta);
								float precioOriginalOferta = Float.parseFloat(json_data.optString("precio_original_oferta").toString());
								Log.e(TAG, "Elem" + i + " precioOriginalOferta " + precioOriginalOferta);
								float precioOfertaOferta = Float.parseFloat(json_data.optString("precio_oferta_oferta").toString());
								Log.e(TAG, "Elem" + i + " precioOfertaOferta " + precioOfertaOferta);
								//fecha_ini_oferta
								// fecha_fin_oferta
								int idCentro = json_data.getInt("id_centro");
								Log.e(TAG, "Elem" + i + " idCentro " + idCentro);
								String nombreCentro = json_data.getString("nombre_centro");
								Log.e(TAG, "Elem" + i + " nombreCentro " + nombreCentro);
								String descripcionCentro = json_data.getString("descripcion_centro");
								Log.e(TAG, "Elem" + i + " descripcionCentro " + descripcionCentro);
								String direccionCentro = json_data.getString("direccion_centro");
								Log.e(TAG, "Elem" + i + " direccionCentro " + direccionCentro);
								int cpCentro = json_data.getInt("cp_centro");
								Log.e(TAG, "Elem" + i + " cpCentro " + cpCentro);
								String provinciaCentro = json_data.getString("provincia_centro");
								Log.e(TAG, "Elem" + i + " provinciaCentro " + provinciaCentro);
								String longCentro = json_data.getString("long_centro");
								Log.e(TAG, "Elem" + i + " longCentro " + longCentro);
								String latCentro = json_data.getString("lat_centro");
								Log.e(TAG, "Elem" + i + " latCentro " + latCentro);
								String telefonoCentro = json_data.getString("telefono_centro");
								Log.e(TAG, "Elem" + i + " telefonoCentro " + telefonoCentro);
								String mailCentro = json_data.getString("mail_centro");
								Log.e(TAG, "Elem" + i + " mailCentro " + mailCentro);
								String webCentro = json_data.getString("web_centro");
								Log.e(TAG, "Elem" + i + " webCentro " + webCentro);

								Oferta o1 = new Oferta();
								o1.setOfertaTitulo(tituloOferta);
								o1.setOfertaDescripcion(descripcionOferta);
								//o1.setOfertaImagen(R.drawable.seas);
								o1.setOfertaImagen("Seas.png");
								o1.setOfertaCondiciones(condicionesOferta);
								o1.setPrecioOriginalOferta(precioOriginalOferta);
								o1.setPrecioOfertaOferta(precioOfertaOferta);
								o1.setIdCentro(idCentro);
								o1.setNombreCentro(nombreCentro);
								o1.setDescripcionCentro(descripcionCentro);
								o1.setDireccionCentro(direccionCentro);
								o1.setCpCentro(cpCentro);
								o1.setProvinciaCentro(provinciaCentro);
								o1.setLongCentro(longCentro);
								o1.setLatCentro(latCentro);
								o1.setTelefonoCentro(telefonoCentro);
								o1.setMailCentro(mailCentro);
								o1.setWebCentro(webCentro);
								ListaOfertasActivity.getInstance().getM_ofertas().add(o1);
								m_ofertas.add(o1);
								GrouponData.setLstOfertas(m_ofertas);
						}
					} else {
						Toast.makeText(ListaOfertasActivity.getInstance().getBaseContext(),
							messageUser, Toast.LENGTH_SHORT).show();
					}
					Log.d(TAG, "ServiciosLista.proceso cargadas ofertas");
					ListaOfertasActivity.getInstance().actualizarListaOfertas();
			}catch (Exception e) {
					Log.e(TAG, "ServiciosLista.miThread proceso sale por catch");
			}
		}
	};
}
