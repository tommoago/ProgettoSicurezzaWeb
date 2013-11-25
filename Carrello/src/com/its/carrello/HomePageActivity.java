package com.its.carrello;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomePageActivity extends Activity {
	EditText mEdtUsername, mEdtPassword;
	private static String mToken;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		setupGUI();
	}

	private void setupGUI() {
		TextView vTxtUsername = (TextView) findViewById(R.id.txt_username);
		vTxtUsername.setText("Username");
		
		TextView vTxtPassword = (TextView) findViewById(R.id.txt_password);
		vTxtPassword.setText("Password");
		
		mEdtUsername = (EditText) findViewById(R.id.edt_username);
		mEdtUsername.setText("Anonimo");
		
		mEdtPassword = (EditText) findViewById(R.id.edt_password);
		mEdtPassword.setText("Password");
		
		Button vBtnLogIn = (Button) findViewById(R.id.btn_log_in);
		vBtnLogIn.setText("Log in");
		vBtnLogIn.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if ( logIn() )
				{
					Intent vIntent = new Intent( HomePageActivity.this, MainPageActivity.class );
					startActivity(vIntent);
					finish();
				}
			}	
		});
	}
	
	private boolean logIn() {
		String vUserName =  mEdtUsername.getText().toString();
		String vPassword =  mEdtPassword.getText().toString();
		String vURL = "http://localhost/ProgettoSicurezzWeb/token?username=%s&password=%s";
		
		AsyncHttpClient client = new AsyncHttpClient();
		client.post( String.format( vURL, vUserName, vPassword ), new AsyncHttpResponseHandler() {
			@Override
		    public void onSuccess(String response) {
		        mToken = response;
		    }
		});
		
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}
	
	public static String getToken( )
	{
		return mToken;
	}

}
