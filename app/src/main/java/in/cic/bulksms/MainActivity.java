package in.cic.bulksms;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class MainActivity extends Activity {
	Button btnSend, importfiles;
	EditText etPhoneNo;
	EditText etMsg;
	ArrayList<String> categoryList;
	String filepath = "";
	static String done;
	private final String LOGTAG = "AndroidFileBrowserExampleActivity";

	private final int REQUEST_CODE_PICK_DIR = 1;
	private final int REQUEST_CODE_PICK_FILE = 2;
	static String newDir;
	static String newFile;
	String newString;
	String csvlast = "csv";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms);

		TextView text = (TextView) findViewById(R.id.textView1);
		btnSend = (Button) findViewById(R.id.sendb);
		etMsg = (EditText) findViewById(R.id.smsf);
		Typeface externalFont = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-BoldItalic.ttf");
		text.setTypeface(externalFont);
		btnSend.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				String msg = etMsg.getText().toString();

				if (msg.length() != 0 && categoryList != null) {

					for (int a = 0; a < categoryList.size(); a++) {
						try {

							SmsManager smsManager = SmsManager.getDefault();
							smsManager.sendTextMessage(categoryList.get(a),
									null, msg, null, null);

						} catch (Exception ex) {
							Toast.makeText(getApplicationContext(),
									ex.getMessage().toString(),
									Toast.LENGTH_SHORT).show();
							ex.printStackTrace();
						}
					}
					Toast.makeText(
							getApplicationContext(),
							"PLEASE WAIT  " + 2 * categoryList.size() + " "
									+ "SECOND" + " To REUSE APP ",
							Toast.LENGTH_SHORT).show();
					etMsg.getText().clear();
					categoryList = null;
				} else {
					Log.e("no message", "enter message");
					Toast.makeText(
							getApplicationContext(),
							" Message Block Is Empty OR Your are not Choosen CSV File",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		final Activity activityForButton = this;

		// Getting address from sdcard

		final ImageView image = (ImageView) findViewById(R.id.imageView1);
		image.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				categoryList = null;
				Intent fileExploreIntent = new Intent(
						in.cic.bulksms.FileBrowserActivity.INTENT_ACTION_SELECT_FILE,
						null, activityForButton,
						in.cic.bulksms.FileBrowserActivity.class);
				startActivityForResult(fileExploreIntent,
						REQUEST_CODE_PICK_FILE);

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE_PICK_DIR) {
			if (resultCode == RESULT_OK) {
				newDir = data
						.getStringExtra(in.cic.bulksms.FileBrowserActivity.returnDirectoryParameter);
				Toast.makeText(
						this,
						"Received DIRECTORY path from file browser  done:\n"
								+ newDir, Toast.LENGTH_LONG).show();

			} else {// if(resultCode == this.RESULT_OK) {
				Toast.makeText(this, "Received NO result from file browser",
						Toast.LENGTH_LONG).show();
			}// END } else {//if(resultCode == this.RESULT_OK) {
		}// if (requestCode == REQUEST_CODE_PICK_DIR) {

		if (requestCode == REQUEST_CODE_PICK_FILE) {
			if (resultCode == RESULT_OK) {
				newFile = data
						.getStringExtra(in.cic.bulksms.FileBrowserActivity.returnFileParameter);

			} else {
                newFile = null;
				Toast.makeText(this, "Received NO result from file browser",
						Toast.LENGTH_SHORT).show();
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
		if (newFile != null && newFile.length() != 0) {

            newString = newFile.substring(newFile.length() - 3);
            if (newString.equals(csvlast)) {
                try {
                    csvdata();
                    Toast.makeText(
                            this,
                            categoryList.size()
                                    + "  CONTACTS HAVE BEEN LOADED FROM CSV FILE",
                            Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(),
                        "This Is Not CSV File Please Select CSV file",
                        Toast.LENGTH_SHORT).show();
            }

		} else {

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void csvdata() throws IOException {
		categoryList = new ArrayList<String>();
		List<String[]> list = new ArrayList<String[]>();
		String next[] = {};
		try {
			// final File dir = Environment.getExternalStorageDirectory();
			// Toast.makeText(getApplicationContext(), "Path: " + newFile,
			// Toast.LENGTH_LONG).show();
			final File yourFile = new File("/" + newFile);
			FileReader csvStreamReader = new FileReader(yourFile);
			// InputStreamReader csvStreamReader = new
			// InputStreamReader(yourFile);

			@SuppressWarnings("resource")
			CSVReader reader = new CSVReader(csvStreamReader);

			for (;;) {
				next = reader.readNext();
				if (next != null) {
					list.add(next);
				} else {
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i)[2] != null) {
					categoryList.add(list.get(i)[2]);
				}

			}
		}
		newFile = null;
	}

}
