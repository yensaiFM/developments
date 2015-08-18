package com.seas.groupon_mvc_php;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.seas.groupon_mvc_php.adaptadores.AdaptadorOfertas;
import com.seas.groupon_mvc_php.beans.Oferta;
import com.seas.groupon_mvc_php.datos.GrouponData;
import com.seas.groupon_mvc_php.threads.ServiciosCategory;
import com.seas.groupon_mvc_php.threads.ServiciosLista;
import com.seas.groupon_mvc_php.tools.DownloadImageTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleActivity extends Activity {
    private TextView ofertaTitulo;
    private TextView ofertaDescripcion;
    private TextView ofertaCondiciones;
    private TextView precioOriginalOferta;
    private TextView precioOfertaOferta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detalle_oferta);
        Oferta detalleOferta = GrouponData.getOfertaSeleccionado();

        TextView ofertaTitulo = (TextView) findViewById(R.id.ofertaTitulo);
        ImageView ofertaImagen = (ImageView) findViewById(R.id.ofertaImagen);
        TextView ofertaDescripcion = (TextView) findViewById(R.id.ofertaDescripcion);
        TextView ofertaCondiciones = (TextView) findViewById(R.id.ofertaCondiciones);
        TextView precioOriginalOferta = (TextView) findViewById(R.id.precioOriginalOferta);
        TextView precioOfertaOferta = (TextView) findViewById(R.id.precioOfertaOferta);
        TextView nombreCentro = (TextView) findViewById(R.id.nombreCentro);
        TextView descripcionCentro = (TextView) findViewById(R.id.descripcionCentro);
        TextView direccionCentro = (TextView) findViewById(R.id.direccionCentro);
        TextView cpCentro = (TextView) findViewById(R.id.cpCentro);
        TextView provinciaCentro = (TextView) findViewById(R.id.provinciaCentro);
        TextView telefonoCentro = (TextView) findViewById(R.id.telefonoCentro);
        TextView mailCentro = (TextView) findViewById(R.id.mailCentro);
        TextView webCentro = (TextView) findViewById(R.id.webCentro);

        if(ofertaTitulo != null){
            ofertaTitulo.setText(detalleOferta.getOfertaTitulo());
        }
        if(ofertaImagen != null){
            String urlImagen = "http://yensai.es/themes/garland/images/groupon/" + detalleOferta.getOfertaImagen();
            //Toast.makeText(this.getBaseContext(), "urlImagen>" + urlImagen, Toast.LENGTH_SHORT).show();
            ofertaImagen.setTag(urlImagen);
            new DownloadImageTask().execute(ofertaImagen);
        }
        if(ofertaDescripcion != null){
            ofertaDescripcion.setText(detalleOferta.getOfertaDescripcion());
        }
        if(ofertaCondiciones != null){
            ofertaCondiciones.setText("Condiciones: " + detalleOferta.getOfertaCondiciones());
        }
        if(precioOriginalOferta != null){
            precioOriginalOferta.setText("Antes: " + detalleOferta.getPrecioOriginalOferta() + "€");
        }
        if(precioOfertaOferta != null){
            precioOfertaOferta.setText("Ahora: " + detalleOferta.getPrecioOfertaOferta() + "€");
        }
        if(nombreCentro != null){
            nombreCentro.setText(detalleOferta.getNombreCentro());
        }
        if(descripcionCentro != null){
            descripcionCentro.setText(detalleOferta.getDescripcionCentro());
        }
        if(direccionCentro != null){
            direccionCentro.setText(detalleOferta.getDireccionCentro());
        }
        if(cpCentro != null){
            cpCentro.setText(String.valueOf(detalleOferta.getCpCentro()));
        }
        if(provinciaCentro != null){
            provinciaCentro.setText(detalleOferta.getProvinciaCentro());
        }
        if(telefonoCentro != null){
            telefonoCentro.setText(detalleOferta.getTelefonoCentro());
        }
        if(mailCentro != null){
            mailCentro.setText(detalleOferta.getMailCentro());
        }
        if(webCentro != null){
            webCentro.setText(detalleOferta.getWebCentro());
        }
    }

}
