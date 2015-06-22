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
 *   ���� �ؾ��� ���׵�
 *   
 *    ::: ��񿡼� ���� �����ͼ� �� ��, �ȳ�
 *    ::: Email ��ȿ���˻�Ф�  :::::::::::::::::�ϼ�
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
				Log.e("aaa", "���̵� �Է����ּ���.");
				join_tv_id.setVisibility(View.VISIBLE);
				join_tv_id.setText("���̵� �Է����ּ���.");
				join_et_id.requestFocus();
			}else if(join_id.length() > 0){
				join_tv_id.setVisibility(View.GONE);
				id=1;
			}
			
			if(join_pwd.length() == 0 ||join_pwd1.length() == 0){
				Log.e("aaa", "��й�ȣ�� �Է����ּ���.");
				join_tv_pwd.setVisibility(View.VISIBLE);
				join_tv_pwd.setText("��й�ȣ�� �Է����ּ���.");
				
				if(join_pwd.length() == 0){
					join_et_pwd.requestFocus();
				}else{
					join_et_pwd1.requestFocus();
				}

				pwd=0;
			}else if(join_pwd.length() < 8){
				Log.e("aaa", "��й�ȣ�� �ּ� 8���̻��Դϴ�.");
				join_tv_pwd.setVisibility(View.VISIBLE);
				join_tv_pwd.setText("��й�ȣ�� �ּ� 8���̻��Դϴ�.");
			}else if(join_pwd.length()>=8){				
				if(!join_et_pwd.getText().toString().equals(join_et_pwd1.getText().toString())){
					join_tv_pwd.setText( "�Է��Ͻ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					join_tv_pwd.setVisibility(View.VISIBLE);
					join_et_pwd1.requestFocus();
				}else{
					join_tv_pwd.setVisibility(View.GONE);
					pwd = 1;
				}
			}
			
			if(join_nick.length() == 0){
				Log.e("aaa", "�г����� �Է����ּ���.");
				join_tv_nick.setVisibility(View.VISIBLE);
				join_tv_nick.setText("�г����� �Է����ּ���.");
				join_et_nick.requestFocus();
			}else if(join_nick.length() > 0){
				join_tv_nick.setVisibility(View.GONE);
				nick=1;
			}
			
			if(join_name.length() == 0){
				join_tv_name.setVisibility(View.VISIBLE);
				join_tv_name.setText("�̸��� �Է����ּ���.");
				join_et_name.requestFocus();
			}else if(join_name.length() > 0){
				join_tv_name.setVisibility(View.GONE);
				name=1;
			}		
			
			if(join_email.length() == 0){
				join_tv_email.setVisibility(View.VISIBLE);
				join_tv_email.setText("�̸��ϸ� �Է����ּ���.");
				join_et_email.requestFocus();
				email=0;
			}else if(!checkEmail(join_email)){
				join_tv_email.setVisibility(View.VISIBLE);
				join_tv_email.setText("�̸��� ���Ŀ� �°� �Է����ּ���.");
				join_et_email.requestFocus();
				email=0;
			}else if(join_email.length() > 0){
				join_tv_email.setVisibility(View.GONE);
				email=1;
			}
			
			if(id == 0 || pwd == 0 || nick == 0 || name == 0 || email == 0){
				System.out.println("���ӵ�������!");
				return;	
			}
		}

		Toast.makeText(this, "������������������������", Toast.LENGTH_SHORT).show();

		bt = new BackgroundTask();
		bt.execute();
		Toast.makeText(this, "�����????????", Toast.LENGTH_SHORT).show();
		
	}
	
	
	// id ��ȿ�� �˻�
	TextWatcher idTextWatcher = new TextWatcher() {
		// ���� ���ڸ� ��밡��.
		
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
				join_tv_id.setText("ù ���ڴ� ������ �����մϴ�.");	
				join_tv_id.setVisibility(View.VISIBLE);
				join_et_id.requestFocus();
			}
			
			if (deleteFlag){
			s.delete(modifyStart,modifyStart+modifyCount);
			deleteFlag=false;
			join_tv_id.setText("������ ���� ���ո� �����մϴ�.");
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
					.setTitle("�α����������� ���ư���")
					.setMessage("ȸ�������� ����Ͻðڽ��ϱ�?")
					.setPositiveButton("��",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent it = new Intent(getApplicationContext(), LoginActivity.class);
									startActivity(it);
									finish();
								}
							}).setNegativeButton("�ƴϿ�", null).show();

			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
    //AsyncTask ������ ����
	class BackgroundTask extends AsyncTask<String, Void, Void>{
		
		protected Void doInBackground(String ... value){
			/*RequestXml_Member.requestGet_memberInsert(requestURL, join_et_id.getText().toString(), 
													  join_et_pwd.getText().toString(),join_et_name.getText().toString(), 
													  join_et_nick.getText().toString(), join_et_email.getText().toString());*/
			requestURL = "http://192.168.0.20:8089/HanOracle/test/board_delete.jsp?parkNum=104";
			RequestXml_Member.requestGet(requestURL);
			return null;
		}
		
		protected void onPostExecute(){	
		}
	}
}
