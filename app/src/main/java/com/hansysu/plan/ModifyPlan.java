package com.hansysu.plan;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hansysu.plan.com.hansysu.DataPicker.DateTimePicker;
import com.hansysu.plan.com.hansysu.sqlite.DbHelper;

public class ModifyPlan extends Activity implements DateTimePicker.TimePickerDialogInterface,View.OnClickListener {
    private EditText describe;
    private TextView startTime;
    private TextView endTime;
    private EditText detail;
    private EditText importance;
    private TextView checkMsg;
    private Button modify;
    private Button complete;
    private boolean isStart = false;
    private DateTimePicker mTimePickerDialog;
    private Plan plan;
    DbHelper dbHelper = new DbHelper(this);
    ContentValues values = new ContentValues();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_detail);
        plan = getIntent().getParcelableExtra("plan");
        mTimePickerDialog = new DateTimePicker(this);
        initView();
    }

    private void initView(){
        describe = (EditText)findViewById(R.id.modify_describe);
        startTime = (TextView)findViewById(R.id.modify_start_time);
        endTime = (TextView)findViewById(R.id.modify_end_time);
        detail  = (EditText)findViewById(R.id.modify_detail);
        importance  = (EditText)findViewById(R.id.modify_importance);
        describe.setText(plan.getDescribe());
        startTime.setText(plan.getStartTime());
        endTime.setText(plan.getEndTime());
        detail.setText(plan.getDetail());
        modify = (Button)findViewById(R.id.modify);
        complete = (Button)findViewById(R.id.complete);
        checkMsg = (TextView)findViewById(R.id.modify_check);
        modify.setOnClickListener(this);
        complete.setOnClickListener(this);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        importance.setText(String.valueOf(plan.getImportance()));
        if(plan.getStatus()!=0){
            modify.setVisibility(View.GONE);
            complete.setVisibility(View.GONE);
            importance.setEnabled(false);
            describe.setEnabled(false);
            detail.setEnabled(false);
        }

    }

    @Override
    public void positiveListener() {
        String Time = mTimePickerDialog.getMonth()+"月"+mTimePickerDialog.getDay()+"日"+mTimePickerDialog.getHour()+"时"+mTimePickerDialog.getMinute()+"分";
        if(isStart){
            startTime.setText(Time);
        }else{
            endTime.setText(Time);
        }

    }

    @Override
    public void negativeListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.modify:

                if(importance.length()>=1&&describe.length()>=1&&!startTime.getText().toString().equals("选择开始时间")&&!endTime.getText().toString().equals("选择结束时间")&&detail.length()>=1){
                    values.put("importace", Integer.parseInt(importance.getText().toString()));
                    values.put("describe", describe.getText().toString());
                    values.put("start_time", startTime.getText().toString());
                    values.put("end_time", endTime.getText().toString());
                    values.put("detail", detail.getText().toString());
                    values.put("status", 0);
                    dbHelper.update(values,plan.getId());
                    dbHelper.close();
                    ModifyPlan.this.finish();
                }else{
                    checkMsg.setVisibility(View.VISIBLE);
                }
                break;
            case  R.id.complete:
                if(importance.length()>=1&&describe.length()>=1&&!startTime.getText().toString().equals("选择开始时间")&&!endTime.getText().toString().equals("选择结束时间")&&detail.length()>=1){
                    values.put("importace", Integer.parseInt(importance.getText().toString()));
                    values.put("describe", describe.getText().toString());
                    values.put("start_time", startTime.getText().toString());
                    values.put("end_time", endTime.getText().toString());
                    values.put("detail", detail.getText().toString());
                    values.put("status", 1);
                    dbHelper.update(values,plan.getId());
                    dbHelper.close();
                    ModifyPlan.this.finish();
                }else{
                    checkMsg.setVisibility(View.VISIBLE);
                }
                break;
            case  R.id.modify_start_time:
                if(plan.getStatus() == 0){
                    isStart = true;
                    mTimePickerDialog.showDateAndTimePickerDialog();
                }
                break;
            case  R.id.modify_end_time:
                if(plan.getStatus() == 0){
                    isStart = false;
                    mTimePickerDialog.showDateAndTimePickerDialog();
                }
                break;
        }

    }
}
