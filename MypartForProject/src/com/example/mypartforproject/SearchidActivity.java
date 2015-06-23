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

public class SearchidActivity extends Activity {

	EditText searchid_et_name;
	EditText searchid_et_email;
	
	TextView searchid_tv_name;
	TextView searchid_tv_email;
	
	BackgroundTask bt;
	String requestURL = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchid);
		
		searchid_et_email = (EditText)findViewById(R.id.searchid_et_email);
		searchid_et_name = (EditText)findViewById(R.id.searchid_et_name);
		
		searchid_tv_email = (TextView)findViewById(R.id.searchid_tv_email);
		searchid_tv_name = (TextView)findViewById(R.id.searchid_tv_name);
	}
	
	public void SearchidButtonClicked(View v){
		/**
		 *  디비에서 받아와서!
		 *  처리
		 *  
		 *  만약에 아이디찾기를 성공하면 다른 액티비티로 넘겨주고,
		 *  실패하면 그냥 Toast 창으로 알려주기!
		 */
		int emailCheck = 0;
		int nameCheck = 0;
		
		int idCheck = 0; // 0 이면 아이디가 존재하지 않음.
		String id = "";
		int i = 0;
		
		if(true){
			if(searchid_et_email.getText().length() == 0){
				searchid_tv_email.setText("이메일을 입력해주세요.");
				searchid_tv_email.setVisibility(View.VISIBLE);
				searchid_et_email.requestFocus();				
			}else if(!checkEmail(searchid_et_email.getText().toString())){
				searchid_tv_email.setText("이메일형식을 확인해주세요.");
				searchid_tv_email.setVisibility(View.VISIBLE);
				searchid_et_email.requestFocus();		
			}else if(searchid_et_email.getText().length() > 0){
				searchid_tv_email.setVisibility(View.GONE);
				emailCheck = 1;
			}
			
			if(searchid_et_name.getText().length() == 0){
				searchid_tv_name.setText("이름을 입력해주세요.");
				searchid_tv_name.setVisibility(View.VISIBLE);
				searchid_et_name.requestFocus();
			}else if(searchid_et_name.getText().length() > 0){
				searchid_tv_name.setVisibility(View.GONE);
				nameCheck = 1;
			}
			
			if(emailCheck == 0 || nameCheck == 0){
				return;
			}
		}
		
		requestURL = "http://192.168.219.103:8338/HanOracle/test/searchMemberId.jsp?email="+searchid_et_email.getText().toString()+"&name="+searchid_et_name.getText().toString();
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
			
			if(result.get(0).toString().equals("1")){
				Intent it = new Intent(getApplicationContext(), SearchidConformActivity.class);
				it.putExtra("SearchId", result.get(1).toString());
				startActivity(it);
				finish();
			}else{
				Toast.makeText(getApplicationContext(), "일치하는 정보가 없습니다.", Toast.LENGTH_SHORT).show();
			}
		}
		
		protected void onCancelled(){
			super.onCancelled();
		}
	}
	
	
	/*
	 * 
		if(i == 0){ // 찾아지는 정보가 있다면!
			Intent it = new Intent(getApplicationContext(), SearchidConformActivity.class);
			startActivity(it);
			finish();
		}else{}
	 * */
}
