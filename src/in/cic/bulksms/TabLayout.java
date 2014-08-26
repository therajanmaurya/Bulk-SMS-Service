package in.cic.bulksms;

import android.app.DialogFragment;
import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class TabLayout extends TabActivity {
	/** Called when the activity is first created. */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TabHost tabHost = getTabHost();

		TabSpec photospec = tabHost.newTabSpec("Using Phone Contacts");
		photospec.setIndicator("Using Phone Contacts");
		Intent photosIntent = new Intent(this, OpenPhoneContact.class);
		photospec.setContent(photosIntent);

		TabSpec songspec = tabHost.newTabSpec("Using CSV file");

		songspec.setIndicator("Using CSV file");
		Intent songsIntent = new Intent(this, MainActivity.class);
		songspec.setContent(songsIntent);

		tabHost.addTab(photospec);
		tabHost.addTab(songspec);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true ;
	}
  
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.about:
		DialogFragment aboutD = new AboutDialog();
		aboutD.show(getFragmentManager(), "ABOUT_DIALOG");
		return true;


		case R.id.share:
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
		    sharingIntent.setType("text/plain");
		    String shareBody = "This App for those wants to send single SMS to unlimited person with in second ";
		    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Bulk SMS Service Android App");
		    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		startActivity(Intent.createChooser(sharingIntent, "Share via"));
		TabLayout.this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.bounce_interpolator);
		return true;


		case R.id.rate:
			Intent rate = new Intent(android.content.Intent.ACTION_VIEW);
            rate.setData(Uri.parse("market://details?id=com.pawan.forcealarm"));
                startActivity(rate);
        		TabLayout.this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
              return true;
		default:

		}
		return true;
	}


}