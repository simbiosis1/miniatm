package org.simbiosis.miniatm;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

	String PREF_NAME = "SIMBIOSIS_MINIATM";

	Context context;

	public AppPreference(Context context) {
		this.context = context;
	}

	public void putString(String key, String value) {

		SharedPreferences prefs = context.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getString(String key) {
		SharedPreferences prefs = context.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		return prefs.getString(key, "");
	}

}
