package com.example.mypartforproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	/**
	 *  �� ���� ��Ƽ��Ƽ�� backpressed �̺�Ʈ �߰���Ű��!!!!!!!!!!!!!!!!!!
	 */

	private BackPressCloseHandler backPressCloseHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // �ӽ÷� �α��� ��Ƽ��Ƽ�� �Ѿ�� �س���.

		backPressCloseHandler = new BackPressCloseHandler(this);
	}
	
	public void GoToLoginActivity(View v){
		Intent it = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(it);
		finish();
	}
	
	public void GoMypageActivity(View v){
		Intent it = new Intent(getApplicationContext(), MypageActivity.class);
		startActivity(it);
		finish();
	}
	
	@Override
	public void onBackPressed(){
		backPressCloseHandler.onBackPressed();
	}
	
}
