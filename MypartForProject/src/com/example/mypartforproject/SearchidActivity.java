package com.example.mypartforproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SearchidActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchid);
	}
	
	public void SearchidButtonClicked(View v){
		
	}
	
	public void LogoButtonClicked(View v){
		Intent it = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(it);
		finish();
	}
}
