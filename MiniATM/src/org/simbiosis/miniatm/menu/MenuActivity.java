/**
 * 
 */
package org.simbiosis.miniatm.menu;

import org.simbiosis.miniatm.R;
import org.simbiosis.miniatm.SavingDepActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author iwanaf
 *
 */
public class MenuActivity extends Activity {

	Button btnSavingDeposit;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		btnSavingDeposit = (Button) findViewById(R.id.btnSavingDeposit);
		btnSavingDeposit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), SavingDepActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public void onBackPressed() {
		new LogoutAsync(this).execute();
	}
}
