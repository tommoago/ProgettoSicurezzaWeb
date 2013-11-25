package com.its.carrello;

import java.util.ArrayList;

import com.its.carrello.adapter.ProductsAdapter;
import com.its.carrello.dataobject.Product;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ProductsActivity extends Activity {
	private ListView mListView;
	private ArrayList<Product> mProducts;
	private AsyncHttpClient mClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);

		getDatas();
		ProductsAdapter vProductAdapter = new ProductsAdapter(mProducts, this);

		mListView = (ListView) findViewById(R.id.lst_products);
		mListView.setAdapter(vProductAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent vIntent = new Intent(ProductsActivity.this,
						ProductsDetailsActivity.class);
				Bundle vBundle = new Bundle();
				vBundle.putInt(ProductsDetailsActivity.KEY_PRODUCT,
						mProducts.get(arg2)._id);
				vIntent.putExtras(vBundle);
				startActivity(vIntent);
			}
		});
	}

	// TODO da sostituire con il get dei prodotti
	private void getDatas() {
		String vURL = "";
		mProducts = new ArrayList<Product>();

		mClient = new AsyncHttpClient();
		mClient.get(vURL, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				System.out.println(response);

			}
		});

		for (int i = 0; i < 50; i++) {
			Product vProduct = new Product();
			vProduct._id = i;
			vProduct.description = "Descrizione prodotto" + (i + 1);
			vProduct.name = "Prodotto " + (i + 1);
			mProducts.add(vProduct);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
