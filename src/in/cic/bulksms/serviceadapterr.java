package in.cic.bulksms;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

@SuppressLint("ViewTag")
class serviceadapterr extends ArrayAdapter<chuchu> {
	ArrayList<String> result = new ArrayList<String>();
	ArrayList<String> checkedno ;

	Activity context;
	ArrayList<String> imageId = new ArrayList<String>();
	List<chuchu> chu;
	private static LayoutInflater inflater = null;

	public serviceadapterr(Activity context, List<chuchu> chu) {
		// TODO Auto-generated constructor stub
		super(context , R.layout.contactslistitem, chu);
		checkedno = new ArrayList<String>();
		this.chu = chu;
		this.context = context;
		inflater = context.getLayoutInflater();
		
	};


	static public class Holder {
		TextView tv, tv1;
		CheckBox check;
	}

	@SuppressLint({ "ViewHolder", "InflateParams", "ViewTag" })
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.e("where", "here");
		final Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = inflater.inflate(R.layout.contactslistitem, null);
			
			holder.tv = (TextView) convertView.findViewById(R.id.heading);
			holder.tv1 = (TextView) convertView.findViewById(R.id.subhead);
			holder.check = (CheckBox) convertView.findViewById(R.id.checkBox1);
			convertView.setTag(holder);
			

		} else {
			holder = (Holder) convertView.getTag();
		}
		convertView.setTag(R.id.checkBox1, holder.check);
		convertView.setTag(R.id.subhead, holder.tv1);
		convertView.setTag(R.id.heading, holder.tv);
		holder.check.setTag(position);

		holder.check
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean arg1) {
						// TODO Auto-generated method stub

						int pos = (Integer) buttonView.getTag();
						chu.get(pos).setSelected(buttonView.isChecked());
						// holder.check.isSelected();
						// holder.check.isSaveFromParentEnabled();
						holder.check.onSaveInstanceState();

						if (holder.check.isChecked()) {
							 
							 
							String ce = chu.get(position).getx();
						

							if (checkedno.contains(ce) == true) {
								Log.d("element in check list can't be added ",
										"not");
								// "check other element");
							} else if (!checkedno.contains(ce) == true) {
								checkedno.add(ce);
								Log.d("element not in check list , added in check list",
										"uncheck other element");
							}

							// checkedno.add(result.get(position));
							// result.remove(position);

						} else if (!holder.check.isChecked()) {
							// int pos = (Integer)buttonView.getTag();
							 //chu.get(pos).setSelected(buttonView.isChecked());
							String ue = chu.get(position).getx();

							if (checkedno.contains(ue) == true) {
								checkedno.remove(ue);
								// String d = checkedno.size() +"";
								// Log.e("uncheck element is removed",
								// checkedno.size());
							}

							// result.add(checkedno.get(position));

							// checkedno.remove(position);

						}

					}
				});
		holder.tv1.setText(chu.get(position).getx1());
		holder.tv.setText(chu.get(position).getx());
		holder.check.setChecked(chu.get(position).isSelected());

		return convertView;

	}

}