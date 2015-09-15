package co.iyubinest.payuchallenge;

import android.app.Application;

public class PayUApp extends Application {

	private Dependencies dependencies;

	@Override
	public void onCreate() {
		super.onCreate();
		initDependencies();
	}

	private void initDependencies() {
		try {
			this.dependencies = new Dependencies(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Dependencies getGlobalDependencies() {
		return dependencies;
	}
}
