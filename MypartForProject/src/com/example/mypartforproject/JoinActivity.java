package com.example.mypartforproject;

import java.io.UnsupportedEncodingException;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class JoinActivity extends Activity{

	EditText join_et_id;
	EditText join_et_pwd;
	EditText join_et_pwd1;
	EditText join_et_name;
	EditText join_et_nick;
	EditText join_et_email;	
	
	TextView join_tv_id;
	TextView join_tv_pwd;
	TextView join_tv_nick;
	TextView join_tv_email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);
		
		join_et_id = (EditText)findViewById(R.id.join_et_id);
		join_et_pwd = (EditText)findViewById(R.id.join_et_pwd);
		join_et_pwd1 = (EditText)findViewById(R.id.join_et_pwd1);
		join_et_name = (EditText)findViewById(R.id.join_et_name);
		join_et_nick = (EditText)findViewById(R.id.join_et_nick);
		join_et_email = (EditText)findViewById(R.id.join_et_email);
		
		join_tv_id = (TextView)findViewById(R.id.join_tv_id);
		join_tv_pwd = (TextView)findViewById(R.id.join_tv_pwd);
		join_tv_nick = (TextView)findViewById(R.id.join_tv_nick);
		join_tv_email = (TextView)findViewById(R.id.join_tv_email);
		/*
		join_et_id.addTextChangedListener(idTextWatcher);
		join_et_pwd.addTextChangedListener(pwdTextWathcer);
		join_et_pwd1.addTextChangedListener(pwd1TextWatcher);
		join_et_nick.addTextChangedListener(nickTextWatcher);
		join_et_name.addTextChangedListener(nameTextWatcher);
		join_et_email.addTextChangedListener(emailTextWathcer);*/
	}
	
	public void LogoButtonClicked(View v){
		Intent it = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(it);
		finish();
	}
	
	public void JoinButtonClicked(View v){
		try{
			System.out.println(join_et_id.toString().getBytes("utf-8").length);
			if(join_et_id.toString().getBytes("utf-8").length == 0){
				join_tv_id.setVisibility(View.VISIBLE);
				join_tv_id.setText("아이디를 입력해주세요.");
				join_et_id.requestFocus();
				return;
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	@Override
	public void onBackPressed() {
		finish();
	}
}
