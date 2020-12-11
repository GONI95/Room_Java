package com.example.room_java;

import androidx.room.TypeConverter;

import java.util.Date;

// 3. Date 객체를 칼럼이 있다. 기본 자료형이 아닌 객체를 사용하기 위해선 TypeConverter를 사용해야 한다
// 3. Appdatabase에서 설정해주면 된다.
public class RoomTypeConverter {
    @TypeConverter
    public static Date fromTimestamp(Long value){
        return value == null ? null : new Date(value);  // 삼항 연산자
    }
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}