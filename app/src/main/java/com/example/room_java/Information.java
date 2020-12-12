package com.example.room_java;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE; // 1. CASCADE 사용을 위해 필요

@Entity(foreignKeys = @ForeignKey(entity = Todo.class,
        parentColumns = "id", childColumns = "sub_id",
        onDelete = CASCADE))
// 1. 외래키 설정
public class Information {
    @PrimaryKey
    private int sub_id;
    @ColumnInfo(name = "information", defaultValue = "")
    private String information;

    public Information(int sub_id, String information) {
        this.sub_id = sub_id;
        this.information = information;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Information{");
        sb.append("id=").append(sub_id);
        sb.append(", information='").append(information).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
