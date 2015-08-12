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

/*
public class AdaptadorOfertas extends BaseAdapter implements Filterable{
    private LayoutInflater mLayoutInflater;
    private ArrayList<Oferta> ofertasList;
    private ArrayList<Oferta> ofertasFilterList;
    private OfertaFilter ofertaFilter;
    private Context context;

    public AdaptadorOfertas(Context context,
                            int disenyoPorFila,
                            ArrayList<Oferta> ofertas) {
        ofertasList = ofertas;
        ofertasFilterList = ofertas;
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return ofertasList.size();
    }

    @Override
    public Oferta getItem(int position) {
        return ofertasList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateOfertasList(ArrayList<Oferta> ofertas) {
        this.ofertasList.clear();
        this.ofertasList.addAll(ofertas);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View updateView;
        ViewHolder viewHolder;
        if (view == null) {
            updateView = mLayoutInflater.inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();

            viewHolder.oTitulo = (TextView) updateView.findViewById(R.id.row_ofertaTitulo);
            viewHolder.oPrecioOriginal = (TextView) updateView.findViewById(R.id.row_precioOriginalOferta);
            viewHolder.oPrecioOferta = (TextView) updateView.findViewById(R.id.row_precioOfertaOferta);

            updateView.setTag(viewHolder);

        } else {
            updateView = view;
            viewHolder = (ViewHolder) updateView.getTag();
        }

        final Oferta item = getItem(position);
        viewHolder.oTitulo.setText(item.getOfertaTitulo());
        viewHolder.oPrecioOriginal.setText("Precio original: " + String.valueOf(item.getPrecioOriginalOferta()) + "€");
        viewHolder.oPrecioOferta.setText("Precio oferta: " + String.valueOf(item.getPrecioOfertaOferta()) + "€");

        return updateView;
    }

    @Override
    public Filter getFilter() {
        if (ofertaFilter == null) {
            ofertaFilter = new OfertaFilter();
        }
        return ofertaFilter;
    }

    static class ViewHolder{
        TextView oTitulo;
        TextView oPrecioOriginal;
        TextView oPrecioOferta;

    }

    private class OfertaFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            long categoryId= Long.parseLong(constraint.toString());
            FilterResults results = new FilterResults();
            if (categoryId > 0) {
                ArrayList<Oferta> filterList = new ArrayList<Oferta>();
                for (int i = 0; i < ofertasFilterList.size(); i++) {
                    if ( (ofertasFilterList.get(i).getIdCategory())== categoryId) {
                        Oferta oferta = ofertasFilterList.get(i);
                        filterList.add(oferta);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = ofertasFilterList.size();
                results.values = ofertasFilterList;
            }
            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            ofertasList = (ArrayList<Oferta>)results.values;
            notifyDataSetChanged();
        }
    }
}*/
