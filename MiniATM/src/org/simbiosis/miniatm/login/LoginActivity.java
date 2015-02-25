package org.simbiosis.miniatm.login;

import org.simbiosis.miniatm.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	Activity activity;

	Button btnLogin;
	EditText edtUser;
	EditText edtPassword;
	ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		setContentView(R.layout.activity_login);

		btnLogin = (Button) findViewById(R.id.btnLogin);
		edtUser = (EditText) findViewById(R.id.edtUser);
		edtPassword = (EditText) findViewById(R.id.edtPassword);

		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showLoading();
				//
				new LoginAsync(activity).execute(edtUser.getText().toString(),
						edtPassword.getText().toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void clear() {
		edtUser.setText("");
		edtPassword.setText("");
		edtUser.requestFocus();
		//
		hideLoading();
	}
	
	public void showLoading(){
		progress = ProgressDialog.show(activity, "Tunggu ...",
				"Menghubungi server ...", true);
		progress.setCancelable(true);
	}
	
	public void hideLoading(){
		progress.dismiss();
	}

}
