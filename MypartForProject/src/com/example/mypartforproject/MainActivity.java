package com.example.mypartforproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	/**
	 *  쩡 오빠 액티비티에 backpressed 이벤트 추가시키기!!!!!!!!!!!!!!!!!!
	 */

	private BackPressCloseHandler backPressCloseHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // 임시로 로그인 액티비티로 넘어가게 해놨음.

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
