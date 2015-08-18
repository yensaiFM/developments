package com.seas.groupon_mvc_php.adaptadores;

import java.util.ArrayList;

import com.seas.groupon_mvc_php.R;
import com.seas.groupon_mvc_php.beans.Oferta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorOfertas extends ArrayAdapter<Oferta>{
    private ArrayList<Oferta> items;
    private Context context;

    public AdaptadorOfertas(Context context,
                            int disenyoPorFila,
                            ArrayList<Oferta> ofertas) {
        super(context, disenyoPorFila, ofertas);
        this.items = ofertas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Oferta getItem(int position) {
        return this.items.get(position);
    }

    public void updateOfertasList(ArrayList<Oferta> ofertas) {
        this.items.clear();
        this.items.addAll(ofertas);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater vi = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item, null);
        }
        Oferta o = items.get(position);
        if(o != null){
            TextView tot = (TextView) v.findViewById(R.id.row_ofertaTitulo);
            TextView tpoof = (TextView) v.findViewById(R.id.row_precioOriginalOferta);
            TextView tpoo = (TextView) v.findViewById(R.id.row_precioOfertaOferta);
            //ImageView im = (ImageView) v.findViewById(R.id.icon);
            //if(im != null){
            //    im.setImageResource(o.getOfertaImagen());
            //}
            if(tot != null){
                tot.setText(o.getOfertaTitulo());
            }
            if(tpoo != null){
                tpoo.setText("Precio oferta: " + String.valueOf(o.getPrecioOfertaOferta()) + "€");
            }
            if(tpoof != null){
                tpoof.setText("Precio original: " + String.valueOf(o.getPrecioOriginalOferta()) + "€");
            }
        }
        return v;
    }
}