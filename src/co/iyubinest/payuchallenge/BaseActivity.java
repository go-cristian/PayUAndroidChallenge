package co.iyubinest.payuchallenge;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(viewId());
		init();
	};

	protected PayUApp getApp() {
		return (PayUApp) getApplication();
	}
	
	protected BaseActivity getBaseActivity() {
		return this;
	}
	
	public abstract void init();

	@LayoutRes
	public abstract int viewId();
}
