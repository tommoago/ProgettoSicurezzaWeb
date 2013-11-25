package com.its.dialog;

import com.its.carrello.ProductsCartActivity;
import com.its.carrello.R;
import com.its.carrello.dataobject.ProductCart;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialogUpdateQuantity {
	private Activity mActivity;
	private Dialog mDialog;
	
	private TextView dialog_title;
	private Button dialog_save, dialog_back;
	private ProductCart mProduct;
	private EditText dialog_quantity;
	private DialogUpdateQuantityListener mListener;
	
	
	public interface DialogUpdateQuantityListener
	{
		void onUpdateNewQuantity(ProductCart mProduct);
	}
	
	public DialogUpdateQuantity ( ProductsCartActivity aActivity )
	{
		mActivity = aActivity;
		mListener = (DialogUpdateQuantityListener) aActivity;
	}
	
	public Dialog getDialog( ProductCart vProduct ) {
		mProduct = vProduct;
		
		mDialog = new Dialog( mActivity, R.style.FullHeightDialog );
		mDialog.setContentView(R.layout.dialog_standard);
		mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		mDialog.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	
		dialog_title = (TextView) mDialog.findViewById (R.id.dialog_title);
		dialog_title.setText( "Modifica ordine" );
		
		dialog_quantity = (EditText) mDialog.findViewById (R.id.dialog_content_quantity);
		dialog_quantity.setText( "" + mProduct.quantity_product );
	
		dialog_save = (Button) mDialog.findViewById (R.id.dialog_save);
		dialog_save.setText( "Modifica" );
		dialog_save.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mProduct.quantity_product = Integer.parseInt( dialog_quantity.getText().toString() );
				mListener.onUpdateNewQuantity( mProduct );
				mDialog.dismiss();
			}
		});
	
		dialog_back = (Button) mDialog.findViewById (R.id.dialog_back);
		dialog_back.setText( "Indietro" );
		dialog_back.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mDialog.dismiss();
			}
		});
		return mDialog;
	}

	public void show() {
		mDialog.show();
	}


}
