package org.simbiosis.miniatm.menu;

import org.simbiosis.miniatm.AppPreference;
import org.simbiosis.miniatm.httpclient.HttpsClient;

import android.app.Activity;
import android.os.AsyncTask;

public class LogoutAsync extends AsyncTask<Void, Void, String> {

	private Activity activity;

	public LogoutAsync(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected String doInBackground(Void... arg) {
		HttpsClient client = new HttpsClient("simbiosis-kop.ddns.net",
				"Koperasi/MicrobankApiCore/logoutSystem");
		AppPreference pref = new AppPreference(activity);
		String sessionName = pref.getString("sessionName");
		client.addParameter("data", sessionName);
		client.post();
		return "";
	}

	protected void onPostExecute(String content) {
		activity.finish();
	}

}
