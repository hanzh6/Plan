package com.hansysu.plan;

import android.os.Parcel;
import android.os.Parcelable;

public class Plan implements Parcelable {


    private int Id;
    private String startTime;
    private String endTime;
    private String describe;
    private int importance;
    private int status;//状态0表示进行中 1表示已完成 2表示已删除
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    private String Detail;
    public Plan(){
        importance = 60;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(describe);
        dest.writeString(Detail);
        dest.writeInt(status);
        dest.writeInt(importance);
    }
    protected Plan(Parcel in) {
        Id = in.readInt();
        startTime = in.readString();
        endTime= in.readString();
        describe= in.readString();
        Detail = in.readString();
        status = in.readInt();
        importance = in.readInt();
    }
    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };
}
