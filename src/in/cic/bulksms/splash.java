package in.cic.bulksms;

import in.cic.bulksms.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class splash extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		Thread time = new Thread(){
			public void run(){
				try{
					sleep(900);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent intent = new Intent(splash.this,TabLayout.class);
					startActivity(intent);
					splash.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
				}
			}
		};
		time.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
