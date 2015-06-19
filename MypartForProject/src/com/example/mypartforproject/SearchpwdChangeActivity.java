package com.example.mypartforproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchpwdChangeActivity extends Activity {

	EditText searchpwdchange_et_pwd;
	EditText searchpwdchange_et_pwd1;
	
	TextView searchpwdchange_tv_pwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchpwdchange);
		
		searchpwdchange_et_pwd = (EditText)findViewById(R.id.searchpwdchange_et_pwd);
		searchpwdchange_et_pwd1 = (EditText)findViewById(R.id.searchpwdchange_et_pwd1);
		
		searchpwdchange_tv_pwd = (TextView)findViewById(R.id.searchpwdchange_tv_pwd);
	}
	
	public void ChangepwdButtonClicked(View v){		
		int pwdCheck = 0;

		if(true){
			if(searchpwdchange_et_pwd.getText().length() == 0 || searchpwdchange_et_pwd1.getText().length() == 0){
				searchpwdchange_tv_pwd.setText("비밀번호를 입력해주세요.");
				searchpwdchange_tv_pwd.setVisibility(View.VISIBLE);
				
				if(searchpwdchange_et_pwd.getText().length() == 0){
					searchpwdchange_et_pwd.requestFocus();
				}else{
					searchpwdchange_et_pwd1.requestFocus();
				}
			}else if(searchpwdchange_et_pwd.getText().length() < 8){
				searchpwdchange_et_pwd.setVisibility(View.VISIBLE);
				searchpwdchange_et_pwd.setText("비밀번호는 최소 8자이상입니다.");			
			}else if(searchpwdchange_et_pwd.getText().length()>=8){
				if(!searchpwdchange_et_pwd.getText().toString().equals(searchpwdchange_et_pwd1.getText().toString())){
					searchpwdchange_tv_pwd.setText( "입력하신 비밀번호가 일치하지 않습니다.");
					searchpwdchange_tv_pwd.setVisibility(View.VISIBLE);
					searchpwdchange_et_pwd.requestFocus();
				}else{
					searchpwdchange_tv_pwd.setVisibility(View.GONE);
					pwdCheck = 1;
				}
			}
			
			if(pwdCheck == 0){
				return;
			}
		}
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
