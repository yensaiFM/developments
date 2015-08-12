package com.seas.groupon_mvc_php.datos;

import java.util.ArrayList;
import java.util.List;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.seas.groupon_mvc_php.beans.Cliente;
import com.seas.groupon_mvc_php.beans.Local;
import com.seas.groupon_mvc_php.beans.Oferta;
import com.seas.groupon_mvc_php.beans.Category;


public class GrouponData {
	private static Cliente cliente;
	private static ArrayList<Oferta> lstOfertas;
	private static Oferta ofertaSeleccionado;
	private static ArrayList<Category> lstCategorias;
	private static Category categorySeleccionado;
	private static ArrayList<Local> lstLocales;
	private static Local localSeleccionado;
	private static ArrayList<OverlayItem> lstOverlays;
	private static OverlayItem overlaySeleccionado;
	
	public static ArrayList<Oferta> getLstOfertas() {
		return lstOfertas;
	}
	public static void setLstOfertas(ArrayList<Oferta> lstOfertas) {
		GrouponData.lstOfertas = lstOfertas;
	}

	public static ArrayList<Category> getLstCategorias() {
		return lstCategorias;
	}
	public static void setLstCategorias(ArrayList<Category> lstCategorias) {
		GrouponData.lstCategorias = lstCategorias;
	}

	public static ArrayList<Local> getLstLocales() {
		return lstLocales;
	}
	public static void setLstLocales(ArrayList<Local> lstLocales) {
		GrouponData.lstLocales = lstLocales;
	}

	public static ArrayList<OverlayItem> getLstOverlays() {
		return lstOverlays;
	}
	public static void setLstOverlays(ArrayList<OverlayItem> lstOverlays) {
		GrouponData.lstOverlays = lstOverlays;
	}

	public static Oferta getOfertaSeleccionado() {
		return ofertaSeleccionado;
	}
	public static void setOfertaSeleccionado(Oferta ofertaSeleccionado) {
		GrouponData.ofertaSeleccionado = ofertaSeleccionado;
	}

	public static Category getCategorySeleccionado() {
		return categorySeleccionado;
	}
	public static void setCategorySeleccionado(Category categorySeleccionado) {
		GrouponData.categorySeleccionado = categorySeleccionado;
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

	public static OverlayItem getOverlaySeleccionado() {
		return overlaySeleccionado;
	}
	public static void setOverlaySeleccionado(OverlayItem overlaySeleccionado) {
		GrouponData.overlaySeleccionado = overlaySeleccionado;
	}
}
