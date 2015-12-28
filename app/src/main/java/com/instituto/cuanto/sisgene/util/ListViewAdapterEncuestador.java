package com.instituto.cuanto.sisgene.util;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.instituto.cuanto.sisgene.R;
import com.instituto.cuanto.sisgene.constantes.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapterEncuestador extends BaseAdapter{

	public ArrayList<HashMap<String, String>> list;
	Activity activity;

	public ListViewAdapterEncuestador(Activity activity, ArrayList<HashMap<String, String>> list){
		super();
		this.activity=activity;
		this.list=list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	private class ViewHolder{
		TextView txtFirst;
		TextView txtSecond;
		TextView txtThird;
		TextView txtFourth;
		TextView txtFive;
		TextView txtSix;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	
		ViewHolder holder;
		
		LayoutInflater inflater=activity.getLayoutInflater();
		
		if(convertView == null){
			
			convertView=inflater.inflate(R.layout.colmn_row_enc, null);
			holder=new ViewHolder();
			
			holder.txtFirst		= (TextView) convertView.findViewById(R.id.TextFirst);
			holder.txtSecond	= (TextView) convertView.findViewById(R.id.TextSecond);
			holder.txtThird		= (TextView) convertView.findViewById(R.id.TextThird);
			holder.txtFourth	= (TextView) convertView.findViewById(R.id.TextFourth);
			holder.txtFive		= (TextView) convertView.findViewById(R.id.TextFive);
			holder.txtSix		= (TextView) convertView.findViewById(R.id.TextSix);
			
			convertView.setTag(holder);
		}else{
			
			holder=(ViewHolder) convertView.getTag();
		}
		
		HashMap<String, String> map=list.get(position);
		holder.txtFirst.setText(map.get(Constants.FIRST_COLUMN));
		holder.txtSecond.setText(map.get(Constants.SECOND_COLUMN));
		holder.txtThird.setText(map.get(Constants.THIRD_COLUMN));
		holder.txtFourth.setText(map.get(Constants.FOURTH_COLUMN));
		holder.txtFive.setText(map.get(Constants.FIVE_COLUMN));
		holder.txtSix.setText(map.get(Constants.SIX_COLUMN));
		
		return convertView;
	}

}
