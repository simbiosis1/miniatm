package org.simbiosis.miniatm.login;

import org.simbiosis.miniatm.AppPreference;
import org.simbiosis.miniatm.httpclient.HttpsClient;
import org.simbiosis.miniatm.menu.MenuActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class LoginAsync extends AsyncTask<String, Void, String> {

	private Activity activity;

	public LoginAsync(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected String doInBackground(String... arg) {
		HttpsClient client = new HttpsClient("simbiosis-kop.ddns.net",
				"Koperasi/MicrobankApiCore/loginSystem");
		client.addParameter("data", arg[0] + ";" + arg[1]);
		return client.post();
	}

	protected void onPostExecute(String content) {
		LoginActivity login = (LoginActivity) activity;
		if (!content.isEmpty()
				&& !content.replaceAll("\n", "").equalsIgnoreCase("ERROR")) {
			// Save session
			AppPreference pref = new AppPreference(activity);
			pref.putString("sessionName", content);
			//
			login.clear();
			//
			Intent i = new Intent(activity, MenuActivity.class);
			activity.startActivity(i);
			// Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		} else {
			login.hideLoading();
			Toast.makeText(activity, "LOGIN GAGAL", Toast.LENGTH_SHORT).show();
		}
	}

}
