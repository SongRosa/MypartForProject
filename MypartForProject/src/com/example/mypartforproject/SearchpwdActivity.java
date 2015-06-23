package com.example.mypartforproject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import requestxml.RequestXml_Member;
import requestxml.getXML;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchpwdActivity extends Activity {

	EditText searchpwd_et_id;
	EditText searchpwd_et_name;
	EditText searchpwd_et_email;
	
	TextView searchpwd_tv_id;
	TextView searchpwd_tv_name;
	TextView searchpwd_tv_email;	

	BackgroundTask bt;
	String requestURL = "";
	
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
		
		requestURL = "http://192.168.219.103:8338/HanOracle/test/searchMemberPwd.jsp?id="+searchpwd_et_id.getText().toString()
																				 +"&email="+searchpwd_et_email.getText().toString()
																				 +"&name="+searchpwd_et_name.getText().toString();
		bt = new BackgroundTask();
		bt.execute();
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
	
	  //AsyncTask 스레드 시작
	class BackgroundTask extends AsyncTask<String, Void, ArrayList>{
			InputStream is;
		protected ArrayList doInBackground(String ... value){
			ArrayList result;
			Log.i("xxxx", "String배열 선언");
			is = RequestXml_Member.requestGet_memberLogin(requestURL);
			Log.i("xxxx", "requestXml 실행.");
			result = getXML.getXml_search(is, requestURL);
			Log.i("xxxx", "getXml 실행");
			return result;
		}
		
		protected void onPostExecute(ArrayList result){
			super.onPostExecute(result);
			Log.i("dddd", "onPostExecute 실행");
			
			if(result.get(0).toString().equals("1")){/*
				Intent it = new Intent(getApplicationContext(), SearchidConformActivity.class);
				it.putExtra("SearchId", result.get(1).toString());
				startActivity(it);
				finish();*/

				Intent it = new Intent(getApplicationContext(), SearchpwdChangeActivity.class);
				it.putExtra("SearchId", result.get(1).toString());
				startActivity(it);
				finish();
			}else{
				Toast.makeText(getApplicationContext(), "일치하는 정보가 없습니다.", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
