package com.its.carrello;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainPageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		
		setupGUI();
	}

	private void setupGUI() {
		TextView vTxtWellcome = (TextView) findViewById(R.id.txt_wellcome);
		vTxtWellcome.setText("Benvenuto!");
		
		Button vShowProducts = (Button) findViewById(R.id.btn_show_products);
		vShowProducts.setText("Guarda la lista dei prodotti");
		vShowProducts.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent vIntent = new Intent( MainPageActivity.this, ProductsActivity.class );
				startActivity(vIntent);
			}	
		});
		
		Button vShowProductsCart = (Button) findViewById(R.id.btn_show_products_cart);
		vShowProductsCart.setText("Vai al carrello");
		vShowProductsCart.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent vIntent = new Intent( MainPageActivity.this, ProductsCartActivity.class );
				startActivity(vIntent);
			}	
		});
		
		Button vExit = (Button) findViewById(R.id.btn_exit);
		vExit.setText("Esci dall'applicazione");
		vExit.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}	
		});
	}

}
