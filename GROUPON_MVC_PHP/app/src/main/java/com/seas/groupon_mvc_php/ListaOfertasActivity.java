package com.seas.groupon_mvc_php;

import java.util.ArrayList;

import com.seas.groupon_mvc_php.adaptadores.AdaptadorCategorias;
import com.seas.groupon_mvc_php.adaptadores.AdaptadorOfertas;
import com.seas.groupon_mvc_php.beans.Oferta;
import com.seas.groupon_mvc_php.beans.Category;
import com.seas.groupon_mvc_php.datos.GrouponData;
import com.seas.groupon_mvc_php.threads.ServiciosCategory;
import com.seas.groupon_mvc_php.threads.ServiciosLista;
import com.seas.groupon_mvc_php.threads.ServiciosTuristicLocation;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class ListaOfertasActivity extends Activity {
	private ArrayList<Oferta> m_ofertas = null;
	public ArrayList<Oferta> getM_ofertas() {
		return m_ofertas;
	}
	public void setM_ofertas(ArrayList<Oferta> m_ofertas) {
		this.m_ofertas = m_ofertas;
	}
	private ArrayList<Category> m_categorias = null;
	public ArrayList<Category> getM_categorias() {
		return m_categorias;
	}
	public void setM_categorias(ArrayList<Category> m_categorias) {
		this.m_categorias = m_categorias;
	}


	private String idc = "";
	public String get_idc () {
		return idc;
	}
	public void set_idc (String idc) {
		this.idc = idc;
	}

	private String idsc = "";
	public String get_idsc () {
		return idsc;
	}
	public void set_idsc (String idsc) {
		this.idsc = idsc;
	}

	private AdaptadorOfertas adaptadorOfertas;
	private ServiciosLista serviciosLista = new ServiciosLista();
	private AdaptadorCategorias adaptadorCategorias;
	private ServiciosCategory serviciosCategory = new ServiciosCategory();
	
	private static ListaOfertasActivity listaOfertasActivity = null;
	public static ListaOfertasActivity getInstance(){
	    	return listaOfertasActivity;
	    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ofertas);
        listaOfertasActivity = this;
        
        ListView lv = (ListView)findViewById(R.id.listView);
		Button btnMapa = (Button) findViewById(R.id.irMapaTodasOferta);
		Spinner spinnerCategory = (Spinner)findViewById(R.id.categorySpinner);

		m_ofertas = new ArrayList<Oferta>();
		m_categorias = new ArrayList<Category>();
        actualizarListaOfertas();
		serviciosLista.miThread(listaOfertasActivity.get_idc(), listaOfertasActivity.get_idsc());
		serviciosCategory.getCategorias();

		// getBaseContext()
		adaptadorCategorias = new AdaptadorCategorias(this, android.R.layout.simple_spinner_item, m_categorias);
		adaptadorCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCategory.setAdapter(adaptadorCategorias);
		adaptadorOfertas = new AdaptadorOfertas(this, R.layout.list_item, m_ofertas);
		lv.setAdapter(this.adaptadorOfertas);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Oferta oferta = (Oferta) parent.getItemAtPosition(position);

				// Almaceno la Oferta seleccionada para que sea accesible
				// desde cualquier punto de la aplicacion
				GrouponData.setOfertaSeleccionado(oferta);

				Intent myIntent = new Intent(getBaseContext(), MapaActivity.class);
				startActivity(myIntent);

				//Bundle bundle = new Bundle();
				//bundle.putString(myKey, local.getLocalName());
				//myIntent.putExtras(bundle);
			}
		});


		btnMapa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mapa = new Intent(ListaOfertasActivity.this, MapaActivity.class);
				mapa.putExtra("idc", listaOfertasActivity.get_idc());
				mapa.putExtra("idsc", listaOfertasActivity.get_idsc());
				startActivity(mapa);
			}
		});
    }
    
   public void actualizarListaOfertas(){
		if(m_ofertas != null && m_ofertas.size() > 0){
			adaptadorOfertas.notifyDataSetChanged();
		}
   }

	public void actualizarListaCategorias(){
		if(m_categorias != null && m_categorias.size() > 0){
			adaptadorCategorias.notifyDataSetChanged();
		}
	}
}
