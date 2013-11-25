package com.its.carrello;

import java.util.ArrayList;

import com.its.carrello.adapter.ProductsCartAdapter;
import com.its.carrello.adapter.ProductsCartAdapter.ProductsCartListener;
import com.its.carrello.dataobject.ProductCart;
import com.its.dialog.DialogUpdateQuantity;
import com.its.dialog.DialogUpdateQuantity.DialogUpdateQuantityListener;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.widget.ListView;

public class ProductsCartActivity extends Activity implements
ProductsCartListener, DialogUpdateQuantityListener {

	private ArrayList<ProductCart> mProductsCart;
	private ListView mListView;
	private ProductCart mProductCart;
	private ProductsCartAdapter mProductsCartAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carrello);
		
		getDatas();
		mProductsCartAdapter = new ProductsCartAdapter( mProductsCart, this);
		
		mListView = (ListView) findViewById (R.id.lst_products_cart);
		mListView.setAdapter( mProductsCartAdapter );
	}

	private void getDatas() {
		mProductsCart = new ArrayList<ProductCart>();
		for ( int i=0; i < 50; i++ )
		{
			ProductCart vProductCart = new ProductCart();
			vProductCart._id = i;
			vProductCart.name_product = "Prodotto" + ( i + 1 );
			vProductCart.quantity_product = ( i + 1 );
			vProductCart.id_product = i+1;
			mProductsCart.add(vProductCart);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.carrello, menu);
		return true;
	}

	@Override
	public void onUpdateQuantity( ProductCart vProductCart ) {
		mProductCart = vProductCart;
		
//		RequestParams params = new RequestParams();
//		params.add( ID_PRODUCT, "" + mIDPRoduct );
//		params.add( QUANTITY, mEdtProductBuy.getText().toString() );
		
		//TODO
//		AsyncHttpClient client = new AsyncHttpClient();
//		client.post( vURL, params, new AsyncHttpResponseHandler() {
//		    @Override
//		    public void onSuccess(String response) {
//		        System.out.println(response);
//		    }
//		});
		
		
		Dialog vDialog = new DialogUpdateQuantity(this).getDialog( mProductCart );
		vDialog.show();
	}

	@Override
	public void onUpdateNewQuantity( ProductCart vProductCart ) {
		mProductCart = vProductCart;
		if ( mProductCart.quantity_product == 0)
			deleteItem( mProductCart.id_product );
		else
			updateItem( mProductCart.id_product, mProductCart.quantity_product );
		
		mProductCart = null;
		//getDatas();
		mProductsCartAdapter.notifyDataSetChanged();
	}

	private void updateItem(int aIDproduct, int aQuantityProduct) {
		for ( int i=0; i < mProductsCart.size(); i++ )
		{
			if ( mProductsCart.get(i).id_product == aIDproduct )
			{
				mProductsCart.get(i).quantity_product = aQuantityProduct;
				break;
			}
		}
		
		
//		RequestParams params = new RequestParams();
//		params.add( ID_PRODUCT, "" + mIDPRoduct );
//		params.add( QUANTITY, mEdtProductBuy.getText().toString() );
//		
//		//TODO
//		AsyncHttpClient client = new AsyncHttpClient();
//		client.post( vURL, params, new AsyncHttpResponseHandler() {
//		    @Override
//		    public void onSuccess(String response) {
//		        System.out.println(response);
//		    }
//		});
	}

	private void deleteItem(int aIDproduct) {
		for ( int i=0; i < mProductsCart.size(); i++ )
		{
			if ( mProductsCart.get(i).id_product == aIDproduct )
			{
				mProductsCart.remove(i);
				break;
			}
		}
		
		
		
//		RequestParams params = new RequestParams();
//		params.add( ID_PRODUCT, "" + mIDPRoduct );
//		params.add( QUANTITY, mEdtProductBuy.getText().toString() );
//		
//		//TODO
//		AsyncHttpClient client = new AsyncHttpClient();
//		client.post( vURL, params, new AsyncHttpResponseHandler() {
//		    @Override
//		    public void onSuccess(String response) {
//		        System.out.println(response);
//		    }
//		});
	}

}
