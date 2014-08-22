package in.cic.bulksms;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OpenPhoneContact extends Activity {

	EditText msg;
	Button Import, send;
	ImageView im;
	static final int PICK_CONTACT_REQUEST = 1;
	static final int REQUEST_CODE_CHECK = 0;

	ArrayList<String> contactsname = new ArrayList<String>();
	ArrayList<String> contactsnumber = new ArrayList<String>();
	ArrayList<String> checkedlist = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.openphonecontact);
		TextView textt = (TextView) findViewById(R.id.textView1);
		Typeface externalFont = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-BoldItalic.ttf");
		textt.setTypeface(externalFont);
		send = (Button) findViewById(R.id.Sendphonecontacts);
		im = (ImageView) findViewById(R.id.imageView1);

		Intent intent = getIntent();
		checkedlist = intent.getStringArrayListExtra("checked_number");

		Cursor phones = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		while (phones.moveToNext()) {

			String name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

			String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			contactsname.add(name);
			contactsnumber.add(phoneNumber);

		}
		phones.close();

		im.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(OpenPhoneContact.this,
						openlistviewcontacts.class);
				i.putStringArrayListExtra("name_array", contactsname);
				i.putStringArrayListExtra("number_array", contactsnumber);
				startActivityForResult(i, REQUEST_CODE_CHECK);

			}
		});

		msg = (EditText) findViewById(R.id.EnterMsgcontact);
		// final String smsg = msg.getText().toString();

		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String smsg = msg.getText().toString();

				if (smsg.length() != 0 && checkedlist != null && checkedlist.size()!=0) {

					for (int a = 0; a < checkedlist.size(); a++) {
						try {

							SmsManager smsManager = SmsManager.getDefault();
							smsManager.sendTextMessage(checkedlist.get(a),
									null, smsg, null, null);
//							 Toast.makeText(getApplicationContext(),
//							 checkedlist.get(a), Toast.LENGTH_SHORT)
//							 .show();

						} catch (NullPointerException ex) {
							Toast.makeText(getApplicationContext(),
									"done notiing",
									Toast.LENGTH_SHORT).show();
							ex.printStackTrace();
						}
					}
					Toast.makeText(
							getApplicationContext(),
							"PLEASE WAIT  " + 2 * checkedlist.size() + " "
									+ "SECOND" + " To REUSE APP ",
							Toast.LENGTH_SHORT).show();
					msg.getText().clear();
					checkedlist = null;

				} else {
					Toast.makeText(
							getApplicationContext(),
							" Message Block Is Empty  or You did not Choosen Contacts",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Bundle basket = data.getExtras();
			checkedlist = basket.getStringArrayList("checked_number");
		} else {
			Toast.makeText(getApplicationContext(),
					"You did not selected any Contact", Toast.LENGTH_SHORT)
					.show();
		}

	}

}
