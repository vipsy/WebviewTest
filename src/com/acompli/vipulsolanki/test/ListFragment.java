package com.acompli.vipulsolanki.test;

import java.util.List;

import com.acompli.vipulsolanki.test.db.Input;
import com.acompli.vipulsolanki.test.db.dao.InputDao;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListFragment extends Fragment {

	private BaseAdapter mAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mAdapter = new ListAdapter(this.getActivity());
		View v = inflater.inflate(R.layout.list_fragment, null);
		ListView listView = (ListView) v.findViewById(R.id.list);
		listView.setAdapter(mAdapter);
		return v;
	}

	public void onDataChanged() {
		if (mAdapter != null) {
			getActivity().runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					mAdapter.notifyDataSetChanged();
				}
			});
		}
	}
	
	class ListAdapter extends BaseAdapter {

		Context mContext;
		List<Input> mData;

		public ListAdapter(Context context) {
			super();
			mContext = context;
			queryData();
		}
		
		@Override
		public void notifyDataSetChanged() {
			queryData();
			super.notifyDataSetChanged();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			ViewHolder holder;
			
			if (view == null) {
				LayoutInflater inflator = getActivity().getLayoutInflater();
				view = inflator.inflate(R.layout.list_item,
						null);
				
				holder = new ViewHolder();
				holder.tv1 =  (TextView) view.findViewById(R.id.text1);
				holder.tv2 =  (TextView) view.findViewById(R.id.text2);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			Input item = getItem(position);
			holder.tv1.setText(item.getName());
			holder.tv2.setText(item.getValue());
			
			return view;
		}

		@Override
		public Input getItem(int position) {
			if (mData == null)
				return null;

			return mData.get(position);
		}

		@Override
		public int getCount() {
			if (mData == null)
				return 0;

			return mData.size();
		}

		@Override
		public long getItemId(int position) {
			return getItem(position).getId();
		}
		
		private void queryData() {
			InputDao dao = new InputDao(mContext);
			mData = dao.listAll();
		}

		class ViewHolder {
			TextView tv1;
			TextView tv2;
		}

	}

}
