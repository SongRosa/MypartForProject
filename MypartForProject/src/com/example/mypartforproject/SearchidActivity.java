package com.example.mypartforproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchidActivity extends Activity {

	EditText searchid_et_name;
	EditText searchid_et_email;
	
	TextView searchid_tv_name;
	TextView searchid_tv_email;
	
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
		
		if(i == 0){ // ã������ ������ �ִٸ�!
			Intent it = new Intent(getApplicationContext(), SearchidConformActivity.class);
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
