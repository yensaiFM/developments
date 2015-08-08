package com.seas.groupon_mvc_php.mapas;



import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.seas.groupon_mvc_php.MapaActivity;
import com.seas.groupon_mvc_php.R;

	public class MiItemizedOverlay extends ItemizedOverlay<OverlayItem>{
		ArrayList<OverlayItem> mOverlays=new ArrayList<OverlayItem>();
		Context mContext;
		public MiItemizedOverlay(Context context) {
			super(boundCenterBottom(MapaActivity.getInstance().getResources().getDrawable(R.drawable.bancos)));
			mContext = context;
		}
		public void addOverlay(OverlayItem overlay) {
			mOverlays.add(overlay);
			populate();
		}
		//Dado una imagen la ajusta para que el punto 0,0 de este este en el centro de la parte inferior
		public Drawable boundCenterBottomAux(Drawable marker){
			return boundCenterBottom(marker);
		}
		@Override
		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return mOverlays.get(i);	
		}
		@Override
		public int size() {
			// TODO Auto-generated method stub
			return mOverlays.size();
		}
		@Override
		protected boolean onTap(int index) {
		  OverlayItem item = mOverlays.get(index);
		  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		  dialog.setTitle(item.getTitle());
		  dialog.setMessage(item.getSnippet());
		  dialog.show();
		  return true;
		}

	}

