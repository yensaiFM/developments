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
import com.seas.groupon_mvc_php.beans.Category;
import com.seas.groupon_mvc_php.tools.Post;
import com.seas.groupon_mvc_php.tools.Utils;
import com.seas.groupon_mvc_php.datos.GrouponData;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ServiciosCategory {
    final Handler handle = new Handler();
    private static String messageUser;
    private JSONArray datos;
    private static final String TAG = "ServiciosCategory";

    public void getCategorias(){
        Thread t = new Thread(){
            public void run(){
                Post post = null;
                try{
                    ArrayList<String> parametros = new ArrayList<String>();
                    //Llamada a Servidor Web PHP
                    post = new Post();
                    datos = post.getServerData(parametros,"http://yensai.es/groupon/categorias.php");
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
                if(datos!=null && datos.length()>0){
                    ArrayList<Category> m_categorias = new ArrayList<Category>();
                    for(int i = 0 ; i <datos.length() ; i++){
                        JSONObject json_data = datos.getJSONObject(i);
                        Log.e(TAG, "Procesa elem" + i);
                        int idCategory = json_data.getInt("id_categoria");
                        //Log.e(TAG, "Elem" + i + " idCategory " + idCategory);
                        String tituloCategory = json_data.getString("titulo_categoria");
                        //Log.e(TAG, "Elem" + i + " TituloCategory " + tituloCategory);

                        Category c1 = new Category();
                        c1.setIdCategory(idCategory);
                        //Log.e(TAG, "Elem" + i + " asigna valor id");
                        c1.setCategory(tituloCategory);
                        //Log.e(TAG, "Elem" + i + " asigna valor category");
                        ListaOfertasActivity.getInstance().getM_categorias().add(c1);
                        //Log.e(TAG, "Elem" + i + " add ");
                        m_categorias.add(c1);
                        //Log.e(TAG, "Elem" + i + " add m_categorias");
                        GrouponData.setLstCategorias(m_categorias);
                        //Log.e(TAG, "Elem" + i + " setLstCategorias");
                    }
                } else {
                    Toast.makeText(ListaOfertasActivity.getInstance().getBaseContext(),
                            messageUser, Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "ServiciosCategory.proceso cargadas categorias");
                ListaOfertasActivity.getInstance().actualizarListaCategorias();
            }catch (Exception e) {
                Log.e(TAG, "ServiciosCategory.getCategorias proceso sale por catch");
            }
        }
    };
}
