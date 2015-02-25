/**
 * 
 */
package org.simbiosis.miniatm;

import java.util.ArrayList;
import java.util.List;

import org.simbiosis.miniatm.model.SavingDv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/**
 * @author iwanaf
 *
 */
public class SearchSavingActivity extends Activity {

	Button btnSearch;
	ListView listview;
	List<SavingDv> list = new ArrayList<SavingDv>();
	SavingArrayAdapter adapter;

	Activity activity;

//	public class MyListActivity extends ListActivity {
//		  public void onCreate(Bundle icicle) {
//		    super.onCreate(icicle);
//		    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//		        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//		        "Linux", "OS/2" };
//		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//		        android.R.layout.simple_list_item_1, values);
//		    setListAdapter(adapter);
//		  }
//
//		  @Override
//		  protected void onListItemClick(ListView l, View v, int position, long id) {
//		    String item = (String) getListAdapter().getItem(position);
//		    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
//		  }
//		} 

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.activity = this;
		setContentView(R.layout.activity_searchsaving);

		btnSearch = (Button) findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				addList();
			}
		});

	}

	private void addList() {
		listview = (ListView) findViewById(R.id.listView);

		list.add(new SavingDv("0011100001", "IWAN AGUS FATAHI", "JL. KEDIRI I NO 188, DEPOK"));
		list.add(new SavingDv("0011100002", "DWI ANINDYANI ROCHMAH", "JL. KEDIRI I NO 188, DEPOK"));
		list.add(new SavingDv("0011100003", "MUHAMMAD FADHLY FATAHI", "JL. KEDIRI I NO 188, DEPOK"));
		list.add(new SavingDv("0011100004", "FATHIMAH ASMA FATAHI", "JL. KEDIRI I NO 188, DEPOK"));
		list.add(new SavingDv("0011100005", "M IQBAL FAHMI", "SURABAYA"));
		list.add(new SavingDv("0011100006", "NAMA LAGI", "SURABAYA"));
		list.add(new SavingDv("0011100007", "PAK DIDIK KMS", "SURABAYA"));
		adapter = new SavingArrayAdapter(this, list);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final SavingDv item = (SavingDv) parent.getItemAtPosition(position);
				Intent resultIntent = new Intent();
				resultIntent.putExtra("HASIL_LISTVIEW", item);
				setResult(Activity.RESULT_OK, resultIntent);
				finish();
				// view.animate().setDuration(2000).alpha(0)
				// .withEndAction(new Runnable() {
				// @Override
				// public void run() {
				// list.remove(item);
				// adapter.notifyDataSetChanged();
				// view.setAlpha(1);
				// }
				// });
			}

		});
	}

}
