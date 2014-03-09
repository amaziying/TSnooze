package com.alarm.tsnooze;

import com.google.android.gms.common.GooglePlayServicesUtil;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class LegalNoticeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_legal_notice);
		
		((TextView) findViewById(R.id.testId))
			.setText(GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(this));
	}

	

}
