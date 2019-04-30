package com.hansysu.plan;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hansysu.plan.com.hansysu.DataPicker.DateTimePicker;
import com.hansysu.plan.com.hansysu.sqlite.DbHelper;

import org.w3c.dom.Text;

public class AddPlanActivity extends Activity implements DateTimePicker.TimePickerDialogInterface,View.OnClickListener {
    private EditText describe;
    private TextView startTime;
    private TextView endTime;
    private EditText detail;
    private EditText importance;
    private TextView checkMsg;
    private Button submit;
    private boolean isStart = false;
    private DateTimePicker mTimePickerDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_plan);
        initView();
        mTimePickerDialog = new DateTimePicker(this);
    }

    private void initView(){
        describe = (EditText)findViewById(R.id.add_describe);
        startTime = (TextView)findViewById(R.id.add_start_time);
        endTime = (TextView)findViewById(R.id.add_end_time);
        detail  = (EditText)findViewById(R.id.add_detail);
        importance  = (EditText)findViewById(R.id.add_importance);
        submit = (Button)findViewById(R.id.add_submit);
        checkMsg = (TextView)findViewById(R.id.add_check);
        submit.setOnClickListener(this);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
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
            case  R.id.add_submit:
                DbHelper dbHelper = new DbHelper(v.getContext());
                ContentValues values = new ContentValues();
                if(importance.length()>=1&&describe.length()>=1&&!startTime.getText().toString().equals("选择开始时间")&&!endTime.getText().toString().equals("选择结束时间")&&detail.length()>=1){
                    values.put("importace", Integer.parseInt(importance.getText().toString()));
                    values.put("describe", describe.getText().toString());
                    values.put("start_time", startTime.getText().toString());
                    values.put("end_time", endTime.getText().toString());
                    values.put("detail", detail.getText().toString());
                    values.put("status", 0);
                    dbHelper.insert(values);
                    AddPlanActivity.this.finish();
                }else{
                    checkMsg.setVisibility(View.VISIBLE);
                }
                break;
            case  R.id.add_start_time:
                isStart = true;
                mTimePickerDialog.showDateAndTimePickerDialog();
                break;
            case  R.id.add_end_time:
                isStart = false;
                mTimePickerDialog.showDateAndTimePickerDialog();
                break;
        }

    }
}


