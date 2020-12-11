package com.example.room_java;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

// 4. 데이터베이스 클래스를 정의
@Database(entities = {Todo.class, Information.class}, version = 1)
@TypeConverters({RoomTypeConverter.class})
// 데이터베이스를 정의하고, entity는 무엇이며, 버전은 몇인지
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
    // AppDatabase 객체가 제공하는 data Access object인 Dao가 필요(이것을 통해 Todo를 조작)
}
