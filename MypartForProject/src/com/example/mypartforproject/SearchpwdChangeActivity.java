package com.example.mypartforproject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.mypartforproject.JoinActivity.BackgroundTask;

import requestxml.RequestXml_Member;
import requestxml.getXML;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchpwdChangeActivity extends Activity {

	EditText searchpwdchange_et_pwd;
	EditText searchpwdchange_et_pwd1;
	
	TextView searchpwdchange_tv_pwd;
	
	String searchId = "";

	BackgroundTask bt;
	String requestURL = "";
	int checkChanged = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchpwdchange);
		
		searchpwdchange_et_pwd = (EditText)findViewById(R.id.searchpwdchange_et_pwd);
		searchpwdchange_et_pwd1 = (EditText)findViewById(R.id.searchpwdchange_et_pwd1);
		
		searchpwdchange_tv_pwd = (TextView)findViewById(R.id.searchpwdchange_tv_pwd);

		Intent it = getIntent();
		searchId = it.getStringExtra("SearchId");
		
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
				searchpwdchange_tv_pwd.setVisibility(View.VISIBLE);
				searchpwdchange_tv_pwd.setText("비밀번호는 최소 8자이상입니다.");			
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
		
		requestURL = "http://192.168.1.45:8338/HanOracle/test/memberUpdatePwd.jsp?id="+searchId+"&pwd="+searchpwdchange_et_pwd.getText().toString();
		bt = new BackgroundTask();
		bt.execute();
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
	
    //AsyncTask 스레드 시작
	class BackgroundTask extends AsyncTask<String, Void, Integer>{
		InputStream is;
		protected Integer doInBackground(String ... value){
	//	is = RequestXml_Member.requestGet_memberLogin(requestURL);
			
			checkChanged = getXML.getXml(is, requestURL);
			
			return checkChanged;
		}
		
		protected void onPostExecute(Integer result){	
			
			if(result == 1){
				Toast.makeText(getApplicationContext(), "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show();
				
				Intent it = new Intent(SearchpwdChangeActivity.this, LoginActivity.class);
				startActivity(it);
				finish();
			}else{
				Toast.makeText(getApplicationContext(), "비밀번호 변경 실패", Toast.LENGTH_SHORT).show();
				return;
			}
		}
	}
}
