package com.example.mypartforproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MypageActivity extends Activity{

	int pwdLayoutCheck = 0;
	int nickLayoutCheck = 0;
	int emailLayoutCheck = 0;
	int nameLayoutCheck = 0;
	int pwd = 0;
	int nick = 0;
	int name = 0;
	int email = 0;
	
	LinearLayout mypage_ll_pwdChange;
	EditText mypage_et_pwd;
	EditText mypage_et_pwd1;
	
	LinearLayout mypage_ll_nickChange;
	EditText mypage_et_nick;

	LinearLayout mypage_ll_nameChange;
	EditText mypage_et_name;
	
	LinearLayout mypage_ll_emailChange;
	EditText mypage_et_email;
	
	
	TextView mypage_tv_pwd;
	TextView mypage_tv_name;
	TextView mypage_tv_nick;
	TextView mypage_tv_email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mypage);
		
		mypage_ll_pwdChange = (LinearLayout)findViewById(R.id.mypage_ll_pwdChange);
		mypage_ll_nickChange = (LinearLayout)findViewById(R.id.mypage_ll_nickChange);
		mypage_ll_nameChange = (LinearLayout)findViewById(R.id.mypage_ll_nameChange);
		mypage_ll_emailChange = (LinearLayout)findViewById(R.id.mypage_ll_emailChange);

		mypage_et_name = (EditText)findViewById(R.id.mypage_et_name);
		mypage_et_nick = (EditText)findViewById(R.id.mypage_et_nick);
		mypage_et_pwd = (EditText)findViewById(R.id.mypage_et_pwd);
		mypage_et_pwd1 = (EditText)findViewById(R.id.mypage_et_pwd1);
		mypage_et_email = (EditText)findViewById(R.id.mypage_et_email);
		
		mypage_tv_pwd = (TextView)findViewById(R.id.mypage_tv_pwd);
		mypage_tv_nick = (TextView)findViewById(R.id.mypage_tv_nick);
		mypage_tv_name = (TextView)findViewById(R.id.mypage_tv_name);
		mypage_tv_email = (TextView)findViewById(R.id.mypage_tv_email);
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
	}

	public void ChangeNickButtonClicked(View v){  // �г��� ���� ��, ��� �ִ� �� �����ͼ� ���� �����ϸ� �������ְ� �ƴϸ� ���
		if(mypage_et_nick.getText().length() == 0){
			mypage_tv_nick.setText("�г����� �Է����ּ���.");
			mypage_tv_nick.setVisibility(View.VISIBLE);
			mypage_et_nick.requestFocus();
			return;
		}else if(mypage_et_nick.getText().length() > 0){
			if(true){ // �̹� ��� �����ϸ�!? �����ؾ���!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				mypage_tv_nick.setText("�̹� �����ϴ� �г����Դϴ�.");
				mypage_tv_nick.setVisibility(View.VISIBLE);
				mypage_et_nick.requestFocus();
				return;
			}else{
				mypage_tv_nick.setVisibility(View.GONE);
				nick = 1;
			}
		}
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
			}else if(true){ //// �̹� �����ϴ� �̸�����.
				mypage_tv_email.setText("�̹� �����ϴ� �̸����Դϴ�.");
				mypage_tv_email.setVisibility(View.VISIBLE);
				mypage_et_email.requestFocus();
				return;				
			}else{
				mypage_tv_email.setVisibility(View.GONE);
			}
		}
	}
	
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
}
