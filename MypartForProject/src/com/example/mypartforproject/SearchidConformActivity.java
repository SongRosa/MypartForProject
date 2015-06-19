package com.example.mypartforproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SearchidConformActivity extends Activity {

	TextView searchidconform_tv_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchidconform);
		
		searchidconform_tv_id = (TextView)findViewById(R.id.searchidconform_tv_id); 
	}
	
	public void GoLoginButtonClicked(View v){
		Intent it = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(it);
		finish();
	}
	
	public void GoSearchpwdButtonClicked(View v){
		Intent it = new Intent(getApplicationContext(), SearchpwdActivity.class);
		startActivity(it);
		finish();		
	}
	
	public void LogoButtonClicked(View v){
		Intent it = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(it);
		finish();
	}
	
	@Override
	public void onBackPressed() {
		Intent it = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(it);
		finish();
	}
}
