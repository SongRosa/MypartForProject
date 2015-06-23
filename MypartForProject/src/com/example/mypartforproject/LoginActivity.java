package com.example.mypartforproject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import requestxml.RequestXml_Member;
import requestxml.getXML;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class LoginActivity extends Activity {

	EditText login_et_id;
	EditText login_et_pwd;

	BackgroundTask bt;
	String requestURL = "";
	
	getXML gx;
	
	CheckBox login_chk_autologin;
	public SharedPreferences setting;
	public SharedPreferences.Editor editor;	
	
	int pwdCheck = 0;
	int dbCheck = 0;
	int goToLogin = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		login_et_id = (EditText)findViewById(R.id.login_et_id);
		login_et_pwd = (EditText)findViewById(R.id.login_et_pwd);
		
		login_chk_autologin = (CheckBox)findViewById(R.id.login_chk_autologin);
		login_chk_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					editor.putString("ID", login_et_id.getText().toString());
					editor.putString("PWD", login_et_pwd.getText().toString());
					editor.putBoolean("Auto_Login_enabled", true);
					editor.commit();
				}else{
					editor.clear();
					editor.commit();
				}
			}
		});

		setting = getSharedPreferences("setting", 0);
		editor = setting.edit();
		
		if(setting.getBoolean("Auto_Login_enabled", false)){
			login_et_id.setText(setting.getString("ID", ""));
			login_et_pwd.setText(setting.getString("PWD", ""));
			login_chk_autologin.setChecked(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void LoginButtonClicked(View v){
		
		if(login_et_id.getText().length() == 0){
			new AlertDialog.Builder(this)
			.setTitle("로그인 실패")
			.setMessage("아이디를 입력해주세요.")
			.setNeutralButton("팝업창 닫기", new DialogInterface.OnClickListener() {					
				@Override
				public void onClick(DialogInterface dialog, int which) {	
					dialog.cancel();
				}
			}).show();		
		}else if(login_et_pwd.getText().length() == 0){
			new AlertDialog.Builder(this)
			.setTitle("로그인 실패")
			.setMessage("비밀번호를 입력해주세요.")
			.setNeutralButton("팝업창 닫기", new DialogInterface.OnClickListener() {					
				@Override
				public void onClick(DialogInterface dialog, int which) {	
					dialog.cancel();
				}
			}).show();				
		}else{			
				requestURL = "http://192.168.1.45:8338/HanOracle/test/memberLogin.jsp?id="+login_et_id.getText().toString()+"&pwd="+login_et_pwd.getText().toString();
				
				Log.i("xxx", requestURL);
				bt = new BackgroundTask();
				bt.execute();
				//bt.onPostExecute();
		}		
	}
	
	public void result(){
		if(goToLogin == 1){
		if(pwdCheck == 1){
			Log.i("yyy", "아이디와 비번이 일치함!!!!");
			Intent it = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(it);
			finish();
		}else if(pwdCheck == 0){
			Log.i("yyy", "아이디와 비밀번호가 일치하지 않습니다");
			new AlertDialog.Builder(this)
				.setTitle("로그인 실패")
				.setMessage("아이디와 비밀번호가 일치하지 않습니다.")
				.setNeutralButton("팝업창 닫기", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {	
						dialog.cancel();
					}
				}).show();				
		}else if(pwdCheck == -1){
			Log.i("yyy", "p아이디가 존재하지 않습니다");
			new AlertDialog.Builder(this)
				.setTitle("로그인 실패")
				.setMessage("아이디가 존재하지 않습니다.")
				.setNeutralButton("팝업창 닫기", new DialogInterface.OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {	
						dialog.cancel();
					}
				}).show();
		}
			goToLogin = 0;
		}
	}
	
	public void SearchidButtonClicked(View v){
		Intent it = new Intent(getApplicationContext(), SearchidActivity.class);
		startActivity(it);
		finish();
	}	
	
	public void SearchpwdButtonClicked(View v){
		Intent it = new Intent(getApplicationContext(), SearchpwdActivity.class);
		startActivity(it);
		finish();
	}	
	
	public void JoinButtonClicked(View v){
		Intent it = new Intent(getApplicationContext(), JoinActivity.class);
		startActivity(it);
		finish();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			new AlertDialog.Builder(this)
					.setTitle("메인으로 돌아가기")
					.setMessage("메인으로 돌아가시겠습니까?")
					.setPositiveButton("네",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent it = new Intent(getApplicationContext(), MainActivity.class);
									startActivity(it);
									finish();
								}
							}).setNegativeButton("아니오", null).show();

			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	  //AsyncTask 스레드 시작
	class BackgroundTask extends AsyncTask<String, Void, Integer>{
		InputStream is;
		
		protected Integer doInBackground(String ... value){
			is = RequestXml_Member.requestGet_memberLogin(requestURL);
			
			pwdCheck = getXML.getXml(is, requestURL);
			
			return pwdCheck;
		}
		
		protected void onPostExecute(Integer result){
			super.onPostExecute(result);
			Log.i("dddd", "onPostExecute 실행");
			goToLogin = 1;
			result();
		}
		
		protected void onCancelled(){
			super.onCancelled();
		}
	}
}
