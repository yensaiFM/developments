package com.seas.groupon_mvc_php;

import com.seas.groupon_mvc_php.threads.ServiceLogin;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private EditText edtEmail;
	private EditText edtPass;
	private Button btnLogin;

	private static LoginActivity loginActivity;
	public static LoginActivity getInstance(){
		return loginActivity;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setupVariables();
		
		loginActivity = this;

		//edtEmail = (EditText) findViewById(R.id.edtEmail);
		//edtPass = (EditText) findViewById(R.id.edtPass);

		edtEmail.setText("a@svalero.com");
		edtPass.setText("1234");
        btnLogin = (Button) findViewById(R.id.btnEnviar);
	        
	        btnLogin.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					ServiceLogin.accionLogin(edtEmail.getText().toString(), edtPass.getText().toString());
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	private void setupVariables() {
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		edtPass = (EditText) findViewById(R.id.edtPass);
	}

}
