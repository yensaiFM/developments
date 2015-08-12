package com.seas.groupon_mvc_php.adaptadores;

import java.util.ArrayList;
import java.util.List;
import com.seas.groupon_mvc_php.R;
import com.seas.groupon_mvc_php.beans.Category;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class AdaptadorCategorias extends ArrayAdapter<Category> {
    private ArrayList<Category> categoryList;
    private Context context;

    public AdaptadorCategorias(Context context, int textViewResourceId,
                       ArrayList<Category> categorias) {
        super(context, textViewResourceId, categorias);
        this.context = context;
        this.categoryList = categorias;
    }

    public int getCount(){
        return this.categoryList.size();
    }

    public Category getItem(int position){
        return this.categoryList.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public void updateCategoriasList(ArrayList<Category> categoryList) {
        this.categoryList.clear();
        this.categoryList.addAll(categoryList);
        this.notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(context);
        view.setTextColor(Color.BLACK);
        view.setGravity(Gravity.CENTER);
        view.setText(categoryList.get(position).getCategory());

        return view;
    }

    //View of Spinner on dropdown Popping
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView view = new TextView(context);
        view.setTextColor(Color.BLACK);
        view.setText(categoryList.get(position).getCategory());
        view.setHeight(60);
        return view;
    }
}
