package com.sqlite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sqlite.R;
import com.sqlite.UpdateDeleteActivity;
import com.sqlite.adapter.RecycleViewAdapter;
import com.sqlite.dal.SQLiteHelper;
import com.sqlite.model.Item;

import java.util.List;

public class FragmentHistory extends Fragment implements RecycleViewAdapter.ItemListener {
	private RecycleViewAdapter adapter;
	private RecyclerView recycleView;
	private SQLiteHelper db;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragmetn_history,container,false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		recycleView=view.findViewById(R.id.recycleView);
		adapter=new RecycleViewAdapter();
		db=new SQLiteHelper(getContext());
		List<Item> list=db.getAll();
		adapter.setList(list);
		LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
		recycleView.setLayoutManager(manager);
		recycleView.setAdapter(adapter);
		adapter.setItemListener(this);
	}

	@Override
	public void onItemClick(View view, int position) {
		Item item=adapter.getItem(position);
		Intent intent=new Intent(getActivity(), UpdateDeleteActivity.class);
		intent.putExtra("item",item);
		startActivity(intent);
	}

	@Override
	public void onResume() {
		super.onResume();
		List<Item> list=db.getAll();
		adapter.setList(list);
	}

}
