package in.cic.bulksms;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class openlistviewcontacts extends Activity {

	ListView listView;
	serviceadapterr adapter;
	ArrayList<String> phonenumber = new ArrayList<String>();
	ArrayList<String> numbername = new ArrayList<String>();
	ArrayList<String> checklist = new ArrayList<String>();
	Button getPhoneContacts;
	public final String shared = "sharedData";
	List<chuchu> chu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opencontactslistview);
		Intent intent = getIntent();
		chu = new ArrayList<chuchu>();
		
		phonenumber = intent.getStringArrayListExtra("number_array");
		numbername = intent.getStringArrayListExtra("name_array");
		// context.getApplicationContext();
//		Toast.makeText(getApplicationContext(), "phone"+phonenumber.size(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(getApplicationContext(), "number"+numbername.size(), Toast.LENGTH_SHORT).show();

		listView = (ListView) findViewById(R.id.contactslistview);
		x();
		Log.e("where", chu.get(0).getx());
		adapter = new serviceadapterr(this, chu);
		Log.e("where", "after adapter");
		listView.setAdapter(adapter);
		Log.e("where", "after setadapter");
		getPhoneContacts = (Button) findViewById(R.id.getphoneC);
		getPhoneContacts.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checklist.addAll(adapter.checkedno);
				String a = "a" + checklist.size();
				Log.d(a, "all contacts");
				Toast.makeText(getApplicationContext(),
						checklist.size() + " SELECTED CONTACTS LOADED",
						Toast.LENGTH_LONG).show();

				Intent result = new Intent();
				Bundle bundle = new Bundle();
				bundle.putStringArrayList("checked_number",checklist);
				result.putExtras(bundle);
				setResult(RESULT_OK, result);
				finish();
				 

			}
		});
	}

	int x() {
		for (int i = 0; i < phonenumber.size(); i++) {
			chuchu chu1 = new chuchu();
			chu1.setx(phonenumber.get(i));
			chu1.setx1(numbername.get(i));
			chu1.setSelected(false);
			chu.add(chu1);
			Log.e("tag", chu.get(i).getx());
		}
		return 1;
	}
}
