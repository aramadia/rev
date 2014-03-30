package com.uwrev.reactiontest;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

public class ReactionTestApplication extends Application {

	public static final String TAG = "ReactionTestApplication";

	@Override
	public void onCreate() {
		super.onCreate();

		Parse.initialize(this, getString(R.string.parse_application_id),
				getString(R.string.parse_client_key));

		// Set your Facebook App Id in strings.xml
		ParseFacebookUtils.initialize(getString(R.string.app_id));

	}

}
