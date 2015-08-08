package com.seas.groupon_mvc_php.threads;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.seas.groupon_mvc_php.ListaOfertasActivity;
import com.seas.groupon_mvc_php.LoginActivity;
import com.seas.groupon_mvc_php.beans.Cliente;
import com.seas.groupon_mvc_php.datos.GrouponData;
import com.seas.groupon_mvc_php.tools.Post;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceLogin{
	private final static Handler manejador = new Handler();
	private static String messageUser; 
	private static JSONArray datos;
	private static Thread threadLogin;

	public static void accionLogin(final String user,final String pass){
		threadLogin = new Thread(){
			public void run(){
				ArrayList<String> parametros = new ArrayList<String>();
				// Obtenemos los valores configurados en Usuario y Contrasena
				parametros.add("Usuario");
				parametros.add(user);
				parametros.add("Contrasena");
				parametros.add(pass);
				// Llamada a Servidor Web PHP
				try {
					Post post = new Post();
					// datos = post.getServerData(parametros,"http://seasmfm.hostzi.com/loginGroupon.php");
					datos = post.getServerData(parametros,"http://yensai.es/groupon/loginGroupon.php");
				} catch (Exception e) {
					messageUser = "Error al conectar con el servidor. ";
				}
				manejador.post(proceso);
			}
		};
		threadLogin.start();
	}
	
	private final static Runnable proceso = new Runnable(){
		public void run() {
			try{
				if (datos != null && datos.length() > 0) {
					JSONObject json_data = datos.getJSONObject(0);
					int numRegistrados = json_data.getInt("ID_USUARIO");
					if (numRegistrados > 0) {
						Cliente cliente = new Cliente();
						cliente.setIdUsuario(numRegistrados);
						cliente.setEmail(json_data.getString("EMAIL"));
						cliente.setPass(json_data.getString("PASS"));
						GrouponData.setCliente(cliente);
						Toast.makeText(LoginActivity.getInstance().getBaseContext(),"" +
								"Usuario correcto. ", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(LoginActivity.getInstance().getBaseContext(), ListaOfertasActivity.class);
					    // Aqui se le puede pasar informacion al intent como el id del elemento o la posicion con
					    // los metodos putExtra
						LoginActivity.getInstance().startActivity(intent);
						//LoginActivity.getInstance().finish();
					}
				} else {
						Toast.makeText(LoginActivity.getInstance().getBaseContext(),"" +
								"Usuario incorrecto. ", Toast.LENGTH_SHORT).show();
						Toast.makeText(LoginActivity.getInstance().getBaseContext(),
								messageUser, Toast.LENGTH_SHORT).show();
				}
			}catch(Exception e){}
		}
	};

}