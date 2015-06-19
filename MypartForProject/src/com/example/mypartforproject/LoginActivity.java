package com.example.mypartforproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	EditText login_et_id;
	EditText login_et_pwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		login_et_id = (EditText)findViewById(R.id.login_et_id);
		login_et_pwd = (EditText)findViewById(R.id.login_et_pwd);
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
		/**
		 *  아이디와 비밀번호가 DB에 저장된 내용과 일치한다면!? i = 0
		 *  비밀번호가 틀렸으면 i = 1
		 *  아이디가 존재하지 않으면  i = 2
		 */
		int i = 2;
		
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
			if(i == 0){
				Intent it = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(it);
				finish();
			}else if(i == 1){
				new AlertDialog.Builder(this)
					.setTitle("로그인 실패")
					.setMessage("아이디와 비밀번호가 일치하지 않습니다.")
					.setNeutralButton("팝업창 닫기", new DialogInterface.OnClickListener() {					
						@Override
						public void onClick(DialogInterface dialog, int which) {	
							dialog.cancel();
						}
					}).show();				
			}else if(i == 2){
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
}
