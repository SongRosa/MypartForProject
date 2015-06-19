package com.example.mypartforproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchpwdActivity extends Activity {

	EditText searchpwd_et_id;
	EditText searchpwd_et_name;
	EditText searchpwd_et_email;
	
	TextView searchpwd_tv_id;
	TextView searchpwd_tv_name;
	TextView searchpwd_tv_email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchpwd);
		
		searchpwd_et_email = (EditText)findViewById(R.id.searchpwd_et_email);
		searchpwd_et_name = (EditText)findViewById(R.id.searchpwd_et_name);
		searchpwd_et_id = (EditText)findViewById(R.id.searchpwd_et_id);
		
		searchpwd_tv_email = (TextView)findViewById(R.id.searchpwd_tv_email);
		searchpwd_tv_name = (TextView)findViewById(R.id.searchpwd_tv_name);
		searchpwd_tv_id = (TextView)findViewById(R.id.searchpwd_tv_id);
	}
	
	
	
	public void SearchpwdButtonClicked(View v){
		int emailCheck = 0;
		int nameCheck = 0;
		int idCheck = 0;
		int i = 0;
		if(true){
			if(searchpwd_et_email.getText().length() == 0){
				searchpwd_tv_email.setText("이메일을 입력해주세요.");
				searchpwd_tv_email.setVisibility(View.VISIBLE);
				searchpwd_et_email.requestFocus();
			}else if(!checkEmail(searchpwd_et_email.getText().toString())){
				searchpwd_tv_email.setText("이메일형식을 입력해주세요.");
				searchpwd_tv_email.setVisibility(View.VISIBLE);
				searchpwd_et_email.requestFocus();
			}else if(searchpwd_et_email.getText().length() > 0){
				searchpwd_tv_email.setVisibility(View.GONE);
				emailCheck = 1;
			}
			

			if(searchpwd_et_name.getText().length() == 0){
				searchpwd_tv_name.setText("이름을 입력해주세요.");
				searchpwd_tv_name.setVisibility(View.VISIBLE);
				searchpwd_et_name.requestFocus();
			}else if(searchpwd_et_name.getText().length() > 0){
				searchpwd_tv_name.setVisibility(View.GONE);
				nameCheck = 1;
			}
			
			if(searchpwd_et_id.getText().length() == 0){
				searchpwd_tv_id.setText("아이디를 입력해주세요.");
				searchpwd_tv_id.setVisibility(View.VISIBLE);
				searchpwd_et_id.requestFocus();
			}else if(searchpwd_et_id.getText().length() > 0){
				searchpwd_tv_id.setVisibility(View.GONE);
				idCheck = 1;
			}
			
			if(emailCheck == 0 || nameCheck == 0 || idCheck == 0){
				return;
			}
		}
		
		if(i == 0){ // 정보에 따른 회원이 존재한다면?? 비밀번호 재설정 창으로 넘어감.
			Intent it = new Intent(getApplicationContext(), SearchpwdChangeActivity.class);
			startActivity(it);
			finish();
		}else{}
	}
	
	public boolean checkEmail(String email){
		String emailregex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		Pattern p = Pattern.compile(emailregex);
		Matcher m = p.matcher(email);
		boolean isNormal = m.matches();
		
		return isNormal;
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
