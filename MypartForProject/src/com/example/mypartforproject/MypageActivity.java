package com.example.mypartforproject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import requestxml.getXML;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MypageActivity extends Activity{

	int pwdLayoutCheck = 0;
	int nickLayoutCheck = 0;
	int emailLayoutCheck = 0;
	int nameLayoutCheck = 0;
	int deleteLayoutCheck = 0;
	int pwd = 0;
	int nick = 0;
	int name = 0;
	int email = 0;
	int check = 0;
	
	
	Context context;
	
	LinearLayout mypage_ll_pwdChange;
	EditText mypage_et_pwd;
	EditText mypage_et_pwd1;
	
	LinearLayout mypage_ll_nickChange;
	EditText mypage_et_nick;

	LinearLayout mypage_ll_nameChange;
	EditText mypage_et_name;
	
	LinearLayout mypage_ll_emailChange;
	EditText mypage_et_email;
	
	LinearLayout mypage_ll_deleteMember;
	TextView mypage_tv_pwd;
	TextView mypage_tv_name;
	TextView mypage_tv_nick;
	TextView mypage_tv_email;

	BackgroundTask bt1;
	BackgroundTask bt2;
	BackgroundTask bt3;
	BackgroundTask bt4;
	
	String requestURL = "";
	String memId = "";
	List<NameValuePair> dataList;
	
	SharedPreferences setting;
	SharedPreferences.Editor editor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mypage);
		
		mypage_ll_pwdChange = (LinearLayout)findViewById(R.id.mypage_ll_pwdChange);
		mypage_ll_nickChange = (LinearLayout)findViewById(R.id.mypage_ll_nickChange);
		mypage_ll_nameChange = (LinearLayout)findViewById(R.id.mypage_ll_nameChange);
		mypage_ll_emailChange = (LinearLayout)findViewById(R.id.mypage_ll_emailChange);
		mypage_ll_deleteMember = (LinearLayout)findViewById(R.id.mypage_ll_deleteMember);

		mypage_et_name = (EditText)findViewById(R.id.mypage_et_name);
		mypage_et_nick = (EditText)findViewById(R.id.mypage_et_nick);
		mypage_et_pwd = (EditText)findViewById(R.id.mypage_et_pwd);
		mypage_et_pwd1 = (EditText)findViewById(R.id.mypage_et_pwd1);
		mypage_et_email = (EditText)findViewById(R.id.mypage_et_email);
		
		mypage_tv_pwd = (TextView)findViewById(R.id.mypage_tv_pwd);
		mypage_tv_nick = (TextView)findViewById(R.id.mypage_tv_nick);
		mypage_tv_name = (TextView)findViewById(R.id.mypage_tv_name);
		mypage_tv_email = (TextView)findViewById(R.id.mypage_tv_email);
		
		Log.i("xxxxx", "��������� ����?");
		
		setting = getSharedPreferences("setting", MODE_PRIVATE);
		editor = setting.edit();
		
		Log.i("xxxxx", "setting");
		memId = setting.getString("ID", "0");
		Log.i("xxxxx", "getString");
		
		Log.i("xxxx", "memId :::: " + memId);
	}

	public void GototheChangePwdButton(View v){
		if(pwdLayoutCheck == 0){
			mypage_ll_pwdChange.setVisibility(View.VISIBLE);			
			pwdLayoutCheck = 1;
		}else if(pwdLayoutCheck == 1){
			mypage_ll_pwdChange.setVisibility(View.GONE);	
			pwdLayoutCheck = 0;
		}
	}
	
	public void GototheChangeNickButton(View v){
		if(nickLayoutCheck == 0){
			mypage_ll_nickChange.setVisibility(View.VISIBLE);
			nickLayoutCheck = 1;
		}else if(nickLayoutCheck == 1){
			mypage_ll_nickChange.setVisibility(View.GONE);
			nickLayoutCheck = 0;
		}
	}
	
	public void GototheChangeNameButton(View v){
		if(nameLayoutCheck == 0){
			mypage_ll_nameChange.setVisibility(View.VISIBLE);
			nameLayoutCheck = 1;
		}else if(nameLayoutCheck == 1){
			mypage_ll_nameChange.setVisibility(View.GONE);
			nameLayoutCheck = 0;
		}
	}
	
	public void GototheChangeEmailButton(View v){
		if(emailLayoutCheck == 0){
			mypage_ll_emailChange.setVisibility(View.VISIBLE);
			emailLayoutCheck = 1;
		}else if(emailLayoutCheck == 1){
			mypage_ll_emailChange.setVisibility(View.GONE);
			emailLayoutCheck = 0;
		}
	}
	public void GototheDeleteMemberButton(View v){
		if(deleteLayoutCheck == 0){
			mypage_ll_deleteMember.setVisibility(View.VISIBLE);
			deleteLayoutCheck = 1;
		}else if(deleteLayoutCheck == 1){
			mypage_ll_deleteMember.setVisibility(View.GONE);
			deleteLayoutCheck = 0;
		}
	}
	
	public void GototheMainButton(View v){
		Intent it = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(it);
		finish();
	}
	
	public void ChangePwdButtonClicked(View v){	 // �Էµ� �� ��� update�������.
		if(mypage_et_pwd.getText().length() == 0 || mypage_et_pwd1.getText().length() == 0){
			mypage_tv_pwd.setText("��й�ȣ�� �Է����ּ���");
			mypage_tv_pwd.setVisibility(View.VISIBLE);
			
			if(mypage_et_pwd.getText().length() == 0){
				mypage_et_pwd.requestFocus();
			}else{
				mypage_et_pwd1.requestFocus();
			}			

			return;
		}else if(mypage_et_pwd.getText().length() < 8){
			mypage_tv_pwd.setText("��й�ȣ�� �ּ� 8���̻��Դϴ�.");
			mypage_tv_pwd.setVisibility(View.VISIBLE);		

			return;
		}else if(mypage_et_pwd.getText().length() >= 8){
			if(!mypage_et_pwd.getText().toString().equals(mypage_et_pwd1.getText().toString())){
				mypage_tv_pwd.setText("�Է��Ͻ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				mypage_tv_pwd.setVisibility(View.VISIBLE);
				mypage_et_pwd1.requestFocus();		
				return;		
			}else{
				mypage_tv_pwd.setVisibility(View.GONE);
				pwd = 1;
			}
		}
	
		check = 1;
		bt1 = new BackgroundTask();
		bt1.execute();
	}

	public void ChangeNickButtonClicked(View v){  // �г��� ���� ��, ��� �ִ� �� �����ͼ� ���� �����ϸ� �������ְ� �ƴϸ� ���
		if(mypage_et_nick.getText().length() == 0){
			mypage_tv_nick.setText("�г����� �Է����ּ���.");
			mypage_tv_nick.setVisibility(View.VISIBLE);
			mypage_et_nick.requestFocus();
			return;
		}
		
		check = 2;
		bt1 = new BackgroundTask();
		bt1.execute();
	}

	public void ChangeNameButtonClicked(View v){ 
		if(mypage_et_name.getText().length() == 0){
			mypage_tv_name.setText("�̸��� �Է����ּ���.");
			mypage_tv_name.setVisibility(View.VISIBLE);
			mypage_et_name.requestFocus();
			return;
		}else{
			mypage_tv_name.setVisibility(View.GONE);
		}
		
		check = 3;
		bt1 = new BackgroundTask();
		bt1.execute();
	}
	
	public void ChangeEmailButtonClicked(View v){
		if(mypage_et_email.getText().length() == 0){
			mypage_tv_email.setText("�̸����� �Է����ּ���.");
			mypage_tv_email.setVisibility(View.VISIBLE);
			mypage_et_email.requestFocus();
			return;
		}else if(mypage_et_email.getText().length() > 0){
			if(!checkEmail(mypage_et_email.getText().toString())){
				mypage_tv_email.setText("�̸��� ���Ŀ� �°� ���ּ���.");
				mypage_tv_email.setVisibility(View.VISIBLE);
				mypage_et_email.requestFocus();
				return;
			}
		}
		
		check = 4;
		bt1 = new BackgroundTask();
		bt1.execute();
	}
	
	public boolean checkEmail(String email){
		String emailregex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
		Pattern p = Pattern.compile(emailregex);
		Matcher m = p.matcher(email);
		boolean isNormal = m.matches();
		
		return isNormal;
	}
	
	public void DeleteMemberButtonClicked(View v){

		check = 5;
		bt1 = new BackgroundTask();
		bt1.execute();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			new AlertDialog.Builder(this)
					.setTitle("�������� ���ư���")
					.setMessage("������������ �����ðڽ��ϱ�?")
					.setPositiveButton("��",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent it = new Intent(getApplicationContext(), MainActivity.class);
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
	
	class BackgroundTask extends AsyncTask<String, Void, Integer>{
		InputStream is;
	protected Integer doInBackground(String ... value){
		int result;	
				
		checkChangeList(check);
		
		Log.i("xxxx", "requestURL");	
		
		result = getXML.getXml(is, requestURL);
		Log.i("xxxx", "getXml ����");
		return result;
	}
	
	protected void onPostExecute(Integer result){
		super.onPostExecute(result);
		Log.i("dddd", "onPostExecute ����");
		
		if(result == 1){		// ��й�ȣ ���� �Ϸ�.
			Toast.makeText(getApplicationContext(), "��й�ȣ�� ����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
			mypage_tv_pwd.setVisibility(View.GONE);
		}else if(result == 2){	// �г��� ���� �Ϸ�.
			Toast.makeText(getApplicationContext(), "�г����� ����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
			mypage_tv_nick.setVisibility(View.GONE);
		}else if(result == 3){	// �г��� �̹� ����.
			Toast.makeText(getApplicationContext(), "�̹� �����ϴ� �г����Դϴ�.", Toast.LENGTH_SHORT).show();
			mypage_tv_nick.setVisibility(View.GONE);
		}else if(result == 4){	// �̸� ���� �Ϸ�.
			Toast.makeText(getApplicationContext(), "�̸��� ����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
			mypage_tv_name.setVisibility(View.GONE);
		}else if(result == 5){	// �̸��� ���� �Ϸ�.
			Toast.makeText(getApplicationContext(), "�̸����� ����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
			mypage_tv_email.setVisibility(View.GONE);
		}else if(result == 6){	// �̸��� �̹� ����.
			Toast.makeText(getApplicationContext(), "�̹� �����ϴ� �̸����Դϴ�.", Toast.LENGTH_SHORT).show();
			mypage_tv_email.setVisibility(View.GONE);
		}else if(result == 7){	// ȸ��Ż��
			Toast.makeText(getApplicationContext(), "ȸ��Ż�� �Ϸ�Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();

			Intent it = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(it);
			finish();			
						
			editor.putString("ID", "");
			editor.putString("PWD", "");
			editor.putBoolean("Auto_Login_enabled", false);
			editor.commit();
		}else{
			Toast.makeText(getApplicationContext(), "ȸ�� ���� ������ �����Ͽ����ϴ�.", Toast.LENGTH_SHORT).show();
			return;
		}
	}
	
	protected void onCancelled(){
		super.onCancelled();
	}
	}
	

	public void checkChangeList(int check){

		dataList = new ArrayList<NameValuePair>();
		
		if(check == 1){
			dataList.add(new BasicNameValuePair("id",  memId));
			dataList.add(new BasicNameValuePair("pwd", mypage_et_pwd.getText().toString()));
			requestURL = "http://192.168.1.45:8338/HanOracle/test/memberUpdatePwd.jsp?" + URLEncodedUtils.format(dataList, "utf-8");
		}else if(check == 2){
			dataList.add(new BasicNameValuePair("id",  memId));
			dataList.add(new BasicNameValuePair("nick", mypage_et_nick.getText().toString()));
			requestURL = "http://192.168.1.45:8338/HanOracle/test/memberUpdateNick.jsp?" + URLEncodedUtils.format(dataList, "utf-8");
		}else if(check == 3){
			dataList.add(new BasicNameValuePair("id",  memId));
			dataList.add(new BasicNameValuePair("name", mypage_et_name.getText().toString()));
			requestURL = "http://192.168.1.45:8338/HanOracle/test/memberUpdateName.jsp?" + URLEncodedUtils.format(dataList, "utf-8");
		}else if(check == 4){
			dataList.add(new BasicNameValuePair("id",  memId));
			dataList.add(new BasicNameValuePair("email", mypage_et_email.getText().toString()));
			requestURL = "http://192.168.1.45:8338/HanOracle/test/memberUpdateEmail.jsp?"+ URLEncodedUtils.format(dataList, "utf-8");
		}else if(check == 5){
			dataList.add(new BasicNameValuePair("id",  memId));
			requestURL = "http://192.168.1.45:8338/HanOracle/test/memberDelete.jsp?"+ URLEncodedUtils.format(dataList, "utf-8");
		}			
	}
}
