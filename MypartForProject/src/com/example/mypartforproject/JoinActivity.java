package com.example.mypartforproject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import requestxml.RequestXml_Member;
import requestxml.getXML;
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
	String requestURL;
	
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
		
		join_et_id.setPrivateImeOptions("defaultInputmode=english;");
		join_et_id.addTextChangedListener(idTextWatcher);		
	}
	
	public void LogoButtonClicked(View v){
		Intent it = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(it);
		finish();
	}
	
	public void JoinButtonClicked(View v){
		if(join_et_id.getText().toString().length() == 0){
			Log.e("aaa", "���̵� �Է����ּ���.");
			join_tv_id.setVisibility(View.VISIBLE);
			join_tv_id.setText("���̵� �Է����ּ���.");
			join_et_id.requestFocus();
			return;
		}else if(join_et_id.getText().toString().length() > 0){
			join_tv_id.setVisibility(View.GONE);
			id=1;
		}
		
		if(join_et_pwd.getText().toString().length() == 0 ||join_et_pwd1.getText().toString().length() == 0){
			Log.e("aaa", "��й�ȣ�� �Է����ּ���.");
			join_tv_pwd.setVisibility(View.VISIBLE);
			join_tv_pwd.setText("��й�ȣ�� �Է����ּ���.");
			
			if(join_et_pwd.getText().toString().length() == 0){
				join_et_pwd.requestFocus();
			}else{
				join_et_pwd1.requestFocus();
			}

			return;
		}else if(join_et_pwd.getText().toString().length() < 8){
			Log.e("aaa", "��й�ȣ�� �ּ� 8���̻��Դϴ�.");
			join_tv_pwd.setVisibility(View.VISIBLE);
			join_tv_pwd.setText("��й�ȣ�� �ּ� 8���̻��Դϴ�.");
			return;
		}else if(join_et_pwd.getText().toString().length()>=8){				
			if(!join_et_pwd.getText().toString().equals(join_et_pwd1.getText().toString())){
				join_tv_pwd.setText( "�Է��Ͻ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				join_tv_pwd.setVisibility(View.VISIBLE);
				join_et_pwd1.requestFocus();
				return;
			}else{
				join_tv_pwd.setVisibility(View.GONE);
				pwd = 1;
			}
		}
		
		if(join_et_nick.getText().toString().length() == 0){
			Log.e("aaa", "�г����� �Է����ּ���.");
			join_tv_nick.setVisibility(View.VISIBLE);
			join_tv_nick.setText("�г����� �Է����ּ���.");
			join_et_nick.requestFocus();
			return;
		}else if(join_et_nick.getText().toString().length() > 0){
			join_tv_nick.setVisibility(View.GONE);
			nick=1;
		}
		
		if(join_et_name.getText().toString().length() == 0){
			join_tv_name.setVisibility(View.VISIBLE);
			join_tv_name.setText("�̸��� �Է����ּ���.");
			join_et_name.requestFocus();
			return;
		}else if(join_et_name.getText().toString().length() > 0){
			join_tv_name.setVisibility(View.GONE);
			name=1;
		}		
		
		if(join_et_email.getText().toString().length() == 0){
			join_tv_email.setVisibility(View.VISIBLE);
			join_tv_email.setText("�̸��ϸ� �Է����ּ���.");
			join_et_email.requestFocus();
			return;
		}else if(!checkEmail(join_et_email.getText().toString())){
			join_tv_email.setVisibility(View.VISIBLE);
			join_tv_email.setText("�̸��� ���Ŀ� �°� �Է����ּ���.");
			join_et_email.requestFocus();
			return;
		}else if(join_et_email.getText().toString().length() > 0){
			join_tv_email.setVisibility(View.GONE);
			email=1;
		}
		
		if(id == 0 || pwd == 0 || nick == 0 || name == 0 || email == 0){
			System.out.println("���Ե�������!");
			return;	
		}
		
		bt = new BackgroundTask();
		bt.execute();
		
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
	class BackgroundTask extends AsyncTask<String, Void, ArrayList>{
		
		InputStream is;
		protected ArrayList doInBackground(String ... value){

			ArrayList result = new ArrayList();
			
			requestUrlList();
			result = getXML.getXml_insert(is, requestURL);
			Log.i("xxxxx", "getXml����");
			return result;
		}
		
		protected void onPostExecute(ArrayList result){	
			
			Log.i("xxxxx", result.get(0).toString());
			Log.i("xxxxx", result.get(1).toString());
			Log.i("xxxxx", result.get(2).toString());
			Log.i("xxxxx", result.get(3).toString());
			
			if(result.get(0).toString().equals("2")){
				Toast.makeText(getApplicationContext(), "ȸ�������� �Ϸ�Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
				
				Intent it = new Intent(JoinActivity.this, LoginActivity.class);
				startActivity(it);
				finish();				
			}else{				
				if(result.get(1).toString().equals("1")){
					join_tv_id.setText("�̹� �����ϴ� ���̵��Դϴ�.");
					join_tv_id.setVisibility(View.VISIBLE);
					join_et_id.setText("");
				}
				if(result.get(2).toString().equals("1")){
					join_tv_nick.setText("�̹� �����ϴ� �г����Դϴ�.");
					join_tv_nick.setVisibility(View.VISIBLE);
					join_et_nick.setText("");
				}
				
				if(result.get(3).toString().equals("1")){
					join_tv_email.setText("�̹� �����ϴ� �̸����Դϴ�.");
					join_tv_email.setVisibility(View.VISIBLE);
					join_et_email.setText("");
				}			
			}		
		}
	}
	
	public void requestUrlList(){		
		List<NameValuePair> dataList = new ArrayList<NameValuePair>();
		dataList.add(new BasicNameValuePair("id",  join_et_id.getText().toString()));
		dataList.add(new BasicNameValuePair("pwd", join_et_pwd.getText().toString()));
		dataList.add(new BasicNameValuePair("nick", join_et_nick.getText().toString()));
		dataList.add(new BasicNameValuePair("name", join_et_name.getText().toString()));
		dataList.add(new BasicNameValuePair("email", join_et_email.getText().toString()));

		requestURL = "http://192.168.1.45:8338/HanOracle/test/memberInsert.jsp?" + URLEncodedUtils.format(dataList, "utf-8");
	}
}
