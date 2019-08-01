package com.ktplay.sample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ktplay.open.KTPlay;
import com.ktplay.open.KTPlay.OnActivityStatusChangedListener;

public class KTSampleActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		KTPlay.startWithAppKey(this, IConfig.APP_KEY, IConfig.APP_SECRET);

		setKTListeners();
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_showkt:
				KTPlay.show();
				break;
		}
	}

	private void showNewMsgHint() {
		this.findViewById(R.id.img_new_msg).setVisibility(View.VISIBLE);
	}

	private void hideNewMsgHint() {
		this.findViewById(R.id.img_new_msg).setVisibility(View.GONE);
	}

	private void setKTListeners() {

		KTPlay.setOnActivityStatusChangedListener(new OnActivityStatusChangedListener() {
			@Override
			public void onActivityChanged(final boolean hasNewActivity) {
				new Handler(Looper.getMainLooper(), new Handler.Callback() {

					@Override
					public boolean handleMessage(Message msg) {
						if (hasNewActivity) {
							showNewMsgHint();
						} else {
							hideNewMsgHint();
						}
						return false;
					}
				}).sendEmptyMessage(0);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		KTPlay.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		KTPlay.onPause(this);
	}
}

