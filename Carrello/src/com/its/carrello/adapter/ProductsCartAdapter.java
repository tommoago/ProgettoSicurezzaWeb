package com.its.carrello.adapter;

import java.util.ArrayList;

import com.its.carrello.R;
import com.its.carrello.dataobject.ProductCart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ProductsCartAdapter extends BaseAdapter{
	private ArrayList<ProductCart> mProductsCart;
	private Context mContext;
	private ProductsCartListener mListener;
	
	public interface ProductsCartListener
	{
		void onUpdateQuantity(ProductCart productCart);
	}

	public ProductsCartAdapter ( ArrayList<ProductCart> aProducts, Context aContext )
	{
		mProductsCart = aProducts;
		mContext = aContext;
		mListener = ( ProductsCartListener ) aContext;
	}
	
	class ViewHolder
	{
		Button vUpdateQuantity;
		TextView vQuantityProduct;
		TextView vNameProduct;
	}
	
    @Override
    public View getView(int aPosition, View aConvertView, ViewGroup aParent)
    {
    	if ( aConvertView == null)
    	{
    		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		aConvertView = inflater.inflate(R.layout.list_item_buggy, null);
    		ViewHolder vHolder = new ViewHolder();
    		
    		vHolder.vUpdateQuantity = (Button) aConvertView.findViewById(R.id.btn_cancel);
    		vHolder.vUpdateQuantity.setText("Modifica");
    		
    		vHolder.vQuantityProduct = (TextView)aConvertView.findViewById(R.id.txt_quantity);
    		vHolder.vQuantityProduct.setTextSize(20);
    		vHolder.vQuantityProduct.setPadding(15, 0, 15, 0);
    		
    		vHolder.vNameProduct = (TextView)aConvertView.findViewById(R.id.txt_product);
    		vHolder.vNameProduct.setTextSize(20);
    		vHolder.vNameProduct.setPadding(0, 0, 15, 0);
    		
    		aConvertView.setPadding(30, 3, 3, 3);
    		aConvertView.setTag(vHolder);
    	}

        
    	final ProductCart vProductCart = (ProductCart) getItem(aPosition);
        ViewHolder vHolder = (ViewHolder) aConvertView.getTag();
        vHolder.vNameProduct.setText( vProductCart.name_product );
        vHolder.vQuantityProduct.setText( "" + vProductCart.quantity_product );
        vHolder.vUpdateQuantity.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {
				mListener.onUpdateQuantity( vProductCart );
			}
        	
        });

        return aConvertView;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mProductsCart.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mProductsCart.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}