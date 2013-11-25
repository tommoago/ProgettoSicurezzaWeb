package com.its.carrello.adapter;

import java.util.ArrayList;

import com.its.carrello.R;
import com.its.carrello.dataobject.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProductsAdapter extends BaseAdapter{
	private ArrayList<Product> mProducts;
	private Context mContext;

	public ProductsAdapter ( ArrayList<Product> aProducts, Context aContext )
	{
		mProducts = aProducts;
		mContext = aContext;
	}
	
	class ViewHolder
	{
		TextView vText;
	}
	
    @Override
    public View getView(int aPosition, View aConvertView, ViewGroup aParent) {
    	if ( aConvertView == null)
    	{
    		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		aConvertView = inflater.inflate(R.layout.list_item_product, null);
    		ViewHolder vHolder = new ViewHolder();
    		vHolder.vText = (TextView)aConvertView.findViewById(R.id.txt_object);
    		vHolder.vText.setTextSize(20);
    		aConvertView.setPadding(30, 3, 3, 3);
    		aConvertView.setTag(vHolder);
    	}
        
        Product vProduct = (Product) getItem(aPosition);
        ViewHolder vHolder = (ViewHolder) aConvertView.getTag();
        vHolder.vText.setText( vProduct.name );

        return aConvertView;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mProducts.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mProducts.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}