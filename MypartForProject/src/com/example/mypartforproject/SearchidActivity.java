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
		 *  ��񿡼� �޾ƿͼ�!
		 *  ó��
		 *  
		 *  ���࿡ ���̵�ã�⸦ �����ϸ� �ٸ� ��Ƽ��Ƽ�� �Ѱ��ְ�,
		 *  �����ϸ� �׳� Toast â���� �˷��ֱ�!
		 */
		int emailCheck = 0;
		int nameCheck = 0;
		
		int idCheck = 0; // 0 �̸� ���̵� �������� ����.
		String id = "";
		int i = 0;
		
		if(true){
			if(searchid_et_email.getText().length() == 0){
				searchid_tv_email.setText("�̸����� �Է����ּ���.");
				searchid_tv_email.setVisibility(View.VISIBLE);
				searchid_et_email.requestFocus();				
			}else if(!checkEmail(searchid_et_email.getText().toString())){
				searchid_tv_email.setText("�̸��������� Ȯ�����ּ���.");
				searchid_tv_email.setVisibility(View.VISIBLE);
				searchid_et_email.requestFocus();		
			}else if(searchid_et_email.getText().length() > 0){
				searchid_tv_email.setVisibility(View.GONE);
				emailCheck = 1;
			}
			
			if(searchid_et_name.getText().length() == 0){
				searchid_tv_name.setText("�̸��� �Է����ּ���.");
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
	
	  //AsyncTask ������ ����
	class BackgroundTask extends AsyncTask<String, Void, ArrayList>{
			InputStream is;
		protected ArrayList doInBackground(String ... value){
			ArrayList result;
			Log.i("xxxx", "String�迭 ����");
			is = RequestXml_Member.requestGet_memberLogin(requestURL);
			Log.i("xxxx", "requestXml ����.");
			result = getXML.getXml_search(is, requestURL);
			Log.i("xxxx", "getXml ����");
			return result;
		}
		
		protected void onPostExecute(ArrayList result){
			super.onPostExecute(result);
			Log.i("dddd", "onPostExecute ����");
			
			if(result.get(0).toString().equals("1")){
				Intent it = new Intent(getApplicationContext(), SearchidConformActivity.class);
				it.putExtra("SearchId", result.get(1).toString());
				startActivity(it);
				finish();
			}else{
				Toast.makeText(getApplicationContext(), "��ġ�ϴ� ������ �����ϴ�.", Toast.LENGTH_SHORT).show();
			}
		}
		
		protected void onCancelled(){
			super.onCancelled();
		}
	}
	
	
	/*
	 * 
		if(i == 0){ // ã������ ������ �ִٸ�!
			Intent it = new Intent(getApplicationContext(), SearchidConformActivity.class);
			startActivity(it);
			finish();
		}else{}
	 * */
}
