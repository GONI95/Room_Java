package com.example.room_java;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;

// 1. 하나의 테이블을 만들었다고 보면 됨, Model 객체
@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)    // 기본 키, 자동 증가
    private int id;
    private String title;
    private Date date;

    public Todo(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    // 1. 접근 할 수 있게 설정자, 접근자 생성
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // 1. 원하는 출력을 위해 toString() 재정의
    @Override
    public String toString() {
        return "번호 : " + id +
                "  내용 : " + title +
                "  날짜 : " + date + "\n\n";
    }
}
