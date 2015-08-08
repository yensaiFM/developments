package com.seas.groupon_mvc_php.datos;

import java.util.ArrayList;

import com.seas.groupon_mvc_php.beans.Cliente;
import com.seas.groupon_mvc_php.beans.Local;
import com.seas.groupon_mvc_php.beans.Oferta;


public class GrouponData {
	private static Cliente cliente;
	private static ArrayList<Oferta> lstOfertas;
	private static Oferta ofertaSeleccionado;
	private static ArrayList<Local> lstLocales;
	private static Local localSeleccionado;
	
	public static ArrayList<Oferta> getLstOfertas() {
		return lstOfertas;
	}
	public static void setLstOfertas(ArrayList<Oferta> lstOfertas) {
		GrouponData.lstOfertas = lstOfertas;
	}

	public static ArrayList<Local> getLstLocales() {
		return lstLocales;
	}
	public static void setLstLocales(ArrayList<Local> lstLocales) {
		GrouponData.lstLocales = lstLocales;
	}

	public static Oferta getOfertaSeleccionado() {
		return ofertaSeleccionado;
	}
	public static void setOfertaSeleccionado(Oferta ofertaSeleccionado) {
		GrouponData.ofertaSeleccionado = ofertaSeleccionado;
	}

	public static Local getLocalSeleccionado() {
		return localSeleccionado;
	}
	public static void setLocalSeleccionado(Local localSeleccionado) {
		GrouponData.localSeleccionado = localSeleccionado;
	}

	public static Cliente getCliente() {
		return cliente;
	}
	public static void setCliente(Cliente cliente) {
		GrouponData.cliente = cliente;
	}
	
}
