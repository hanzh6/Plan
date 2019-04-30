package com.hansysu.plan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanListAdapter extends BaseAdapter {
    private ArrayList<Plan> planList;
    private LayoutInflater inflater;
    public PlanListAdapter() {}

    public PlanListAdapter(ArrayList<Plan> planList,Context context) {
        this.planList = planList;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return planList==null?0:planList.size();
    }

    @Override
    public Plan getItem(int position) {
        return planList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view=inflater.inflate(R.layout.plan_item,parent,false);
        Plan plan=getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView number= (TextView) view.findViewById(R.id.paln_number);
        TextView describe= (TextView) view.findViewById(R.id.plan_describe);
        TextView importance= (TextView) view.findViewById(R.id.importance);
        TextView start= (TextView) view.findViewById(R.id.item_start_time);
        TextView end= (TextView) view.findViewById(R.id.item_end_time);
        number.setText(String.valueOf(position));
        describe.setText(plan.getDescribe());
        start.setText(plan.getStartTime());
        end.setText(plan.getStartTime());
        importance.setText(String.valueOf(plan.getImportance()));
        return view;
    }
}
