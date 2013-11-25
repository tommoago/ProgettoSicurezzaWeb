package com.its.carrello;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProductsDetailsActivity extends Activity {
	protected static final String KEY_PRODUCT = "KEY_PRODUCT";
	protected static final String QUANTITY = "quantity";
	private static String ID_PRODUCT = "id_product";
	
	TextView mTxtProductName, mTxtProductDescription;
	EditText mEdtProductBuy;
	private int mIDPRoduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_produtct);
		
		TextView vLabelProductName = (TextView) findViewById(R.id.label_product_name);
		vLabelProductName.setText("Nome prodotto: ");
		
		TextView vLabelProductDescription = (TextView) findViewById(R.id.label_product_description);
		vLabelProductDescription.setText("Descrizione prodotto: ");
		
		TextView vLabelProductBuy = (TextView) findViewById(R.id.label_product_buy);
		vLabelProductBuy.setText("Prodotti ordinati");
		
		Button vBtnBuyProduct = (Button) findViewById(R.id.btn_buy_product);
		vBtnBuyProduct.setText("Ordina");
		vBtnBuyProduct.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				String vURL = "";
				RequestParams params = new RequestParams();
				params.add( ID_PRODUCT, "" + mIDPRoduct );
				params.add( QUANTITY, mEdtProductBuy.getText().toString() );
				
				//TODO
				AsyncHttpClient client = new AsyncHttpClient();
				client.post( vURL, params, new AsyncHttpResponseHandler() {
				    @Override
				    public void onSuccess(String response) {
				        System.out.println(response);
				    }
				});
			}		
		});
		
		
		
		mTxtProductName = (TextView) findViewById(R.id.txt_product_name);
		mTxtProductDescription = (TextView) findViewById(R.id.txt_product_description);
		
		Bundle vBundle = getIntent().getExtras();
		if ( vBundle != null )
		{
			final int mIDPRoduct = vBundle.getInt( KEY_PRODUCT );
			String vURL = "";
			
			
			//TODO
			AsyncHttpClient client = new AsyncHttpClient();
			client.get( vURL, null, new AsyncHttpResponseHandler() {
			    @Override
			    public void onSuccess(String response) {
			        System.out.println(response);
					mTxtProductName.setText( "Prodotto " + ( mIDPRoduct + 1) );
					mTxtProductDescription.setText( "Descrizione prodotto " + ( mIDPRoduct + 1) );
			    }
			});

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.details_produtct, menu);
		return true;
	}

}
