package com.hansysu.plan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hansysu.plan.com.hansysu.sqlite.DbHelper;

import java.util.ArrayList;

public class CompletePlan extends Fragment implements ListView.OnItemClickListener{
    ListView listView;
    ArrayList<Plan> planList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_progress, container, false);
        listView = (ListView) v.findViewById(R.id.progress_list);
        return v;
    }
    @Override
    public void onResume() {
        DbHelper db = new DbHelper(getActivity());
        Cursor cursor = db.query(1);
        planList = getPlanFromCursor(cursor);
        PlanListAdapter adapter=new PlanListAdapter(planList,getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        super.onResume();
    }

    ArrayList<Plan> getPlanFromCursor(Cursor cursor){
        ArrayList<Plan> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Plan plan = new Plan();
                plan.setDescribe(cursor.getString(2));
                plan.setEndTime(cursor.getString(5));
                plan.setImportance(cursor.getInt(1));
                plan.setDetail(cursor.getString(3));
                plan.setStartTime(cursor.getString(4));
                plan.setStatus(cursor.getInt(6));
                list.add(plan);
            } while (cursor.moveToNext());
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),ModifyPlan.class);
        intent.putExtra("plan",planList.get(position));
        startActivity(intent);
    }
}
