package org.simbiosis.miniatm;

import java.util.List;

import org.simbiosis.miniatm.model.SavingDv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SavingArrayAdapter extends ArrayAdapter<SavingDv> {
	private Context context;
	private List<SavingDv> values;

	public SavingArrayAdapter(Context context, List<SavingDv> values) {
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = values;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		TextView textView1 = (TextView) rowView.findViewById(R.id.row1);
		TextView textView2 = (TextView) rowView.findViewById(R.id.row2);
		SavingDv data = values.get(position);
		textView1.setText(data.getCode() + "-" + data.getName());
		textView2.setText(data.getAddress());
		return rowView;
	}
}
