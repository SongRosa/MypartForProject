package com.example.mypartforproject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Text;

import requestxml.RequestXml_Member;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/***
 *   焼送 背醤拝 紫牌級
 *   
 *    ::: 巨搾拭辞 舛左 亜閃人辞 搾嘘 板, 照鎧
 *    ::: Email 政反失伊紫ばば  :::::::::::::::::刃失
 */
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
	TextView join_tv_name;
	
	int id = 0;
	int pwd = 0;
	int name = 0;
	int nick = 0;
	int email = 0;
	
	BackgroundTask bt;
	String requestURL = "http://192.168.0.20:8089/HanOracle/test/memberInsert.jsp";
	
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
		join_tv_name = (TextView)findViewById(R.id.join_tv_name);
		
		join_et_id.addTextChangedListener(idTextWatcher);		
	}
	
	public void LogoButtonClicked(View v){
		Intent it = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(it);
		finish();
	}
	
	public void JoinButtonClicked(View v){
		
		String join_id = join_et_id.getText().toString();
		String join_pwd = join_et_pwd.getText().toString();
		String join_pwd1 = join_et_pwd1.getText().toString();
		String join_name = join_et_name.getText().toString();
		String join_nick = join_et_nick.getText().toString();
		String join_email = join_et_email.getText().toString();
		
		if(true){
			if(join_id.length() == 0){
				Log.e("aaa", "焼戚巨研 脊径背爽室推.");
				join_tv_id.setVisibility(View.VISIBLE);
				join_tv_id.setText("焼戚巨研 脊径背爽室推.");
				join_et_id.requestFocus();
			}else if(join_id.length() > 0){
				join_tv_id.setVisibility(View.GONE);
				id=1;
			}
			
			if(join_pwd.length() == 0 ||join_pwd1.length() == 0){
				Log.e("aaa", "搾腔腰硲研 脊径背爽室推.");
				join_tv_pwd.setVisibility(View.VISIBLE);
				join_tv_pwd.setText("搾腔腰硲研 脊径背爽室推.");
				
				if(join_pwd.length() == 0){
					join_et_pwd.requestFocus();
				}else{
					join_et_pwd1.requestFocus();
				}

				pwd=0;
			}else if(join_pwd.length() < 8){
				Log.e("aaa", "搾腔腰硲澗 置社 8切戚雌脊艦陥.");
				join_tv_pwd.setVisibility(View.VISIBLE);
				join_tv_pwd.setText("搾腔腰硲澗 置社 8切戚雌脊艦陥.");
			}else if(join_pwd.length()>=8){				
				if(!join_et_pwd.getText().toString().equals(join_et_pwd1.getText().toString())){
					join_tv_pwd.setText( "脊径馬重 搾腔腰硲亜 析帖馬走 省柔艦陥.");
					join_tv_pwd.setVisibility(View.VISIBLE);
					join_et_pwd1.requestFocus();
				}else{
					join_tv_pwd.setVisibility(View.GONE);
					pwd = 1;
				}
			}
			
			if(join_nick.length() == 0){
				Log.e("aaa", "莞革績聖 脊径背爽室推.");
				join_tv_nick.setVisibility(View.VISIBLE);
				join_tv_nick.setText("莞革績聖 脊径背爽室推.");
				join_et_nick.requestFocus();
			}else if(join_nick.length() > 0){
				join_tv_nick.setVisibility(View.GONE);
				nick=1;
			}
			
			if(join_name.length() == 0){
				join_tv_name.setVisibility(View.VISIBLE);
				join_tv_name.setText("戚硯研 脊径背爽室推.");
				join_et_name.requestFocus();
			}else if(join_name.length() > 0){
				join_tv_name.setVisibility(View.GONE);
				name=1;
			}		
			
			if(join_email.length() == 0){
				join_tv_email.setVisibility(View.VISIBLE);
				join_tv_email.setText("戚五析研 脊径背爽室推.");
				join_et_email.requestFocus();
				email=0;
			}else if(!checkEmail(join_email)){
				join_tv_email.setVisibility(View.VISIBLE);
				join_tv_email.setText("戚五析 莫縦拭 限惟 脊径背爽室推.");
				join_et_email.requestFocus();
				email=0;
			}else if(join_email.length() > 0){
				join_tv_email.setVisibility(View.GONE);
				email=1;
			}
			
			if(id == 0 || pwd == 0 || nick == 0 || name == 0 || email == 0){
				System.out.println("諮績鞠走省製!");
				return;	
			}
		}

		Toast.makeText(this, "しししししししししししし", Toast.LENGTH_SHORT).show();

		bt = new BackgroundTask();
		bt.execute();
		Toast.makeText(this, "叔楳喫????????", Toast.LENGTH_SHORT).show();
		
	}
	
	
	// id 政反失 伊紫
	TextWatcher idTextWatcher = new TextWatcher() {
		// 慎庚 収切幻 買遂亜管.
		
		int modifyCount = 0;
		int modifyStart = 0;
		boolean deleteFlag = false;
		final String idregex = "[a-zA-Z0-9]*";
		final String numregex = "^[0-9]";
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			join_tv_id.setVisibility(View.GONE);
			modifyCount = count;
			modifyStart = start;
			deleteFlag = !s.subSequence(modifyStart,modifyStart+modifyCount).toString().matches(idregex);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}
		
		@Override
		public void afterTextChanged(Editable s) {				
			if(s.toString().matches(numregex)){
				s.delete(modifyStart,modifyStart+modifyCount);
				join_tv_id.setText("湛 越切澗 慎庚幻 亜管杯艦陥.");	
				join_tv_id.setVisibility(View.VISIBLE);
				join_et_id.requestFocus();
			}
			
			if (deleteFlag){
			s.delete(modifyStart,modifyStart+modifyCount);
			deleteFlag=false;
			join_tv_id.setText("慎庚引 収切 繕杯幻 亜管杯艦陥.");
			join_tv_id.setVisibility(View.VISIBLE);
			join_et_id.requestFocus();			
			}		
			
			
		}
	};
	
	public boolean checkEmail(String email){
		String emailregex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		Pattern p = Pattern.compile(emailregex);
		Matcher m = p.matcher(email);
		boolean isNormal = m.matches();
		
		return isNormal;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			new AlertDialog.Builder(this)
					.setTitle("稽益昔凪戚走稽 宜焼亜奄")
					.setMessage("噺据亜脊聖 昼社馬獣畏柔艦猿?")
					.setPositiveButton("革",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent it = new Intent(getApplicationContext(), LoginActivity.class);
									startActivity(it);
									finish();
								}
							}).setNegativeButton("焼艦神", null).show();

			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
    //AsyncTask 什傾球 獣拙
	class BackgroundTask extends AsyncTask<String, Void, Void>{
		
		protected Void doInBackground(String ... value){
			/*RequestXml_Member.requestGet_memberInsert(requestURL, join_et_id.getText().toString(), 
													  join_et_pwd.getText().toString(),join_et_name.getText().toString(), 
													  join_et_nick.getText().toString(), join_et_email.getText().toString());*/
			requestURL = "http://192.168.0.20:8089/HanOracle/test/board_delete.jsp?parkNum=81";
			RequestXml_Member.requestGet(requestURL);
			return null;
		}
		
		protected void onPostExecute(){	
		}
	}
}
