package com.example.room_java;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// 2. Todo객체에 접근하기 위한 data Access object(interface)
@Dao
public interface TodoDao {
    @Query("SELECT * FROM TODO")
    LiveData<List<Todo>> getAll_todo(); // Todo테이블에 여러 튜플을 가질 수 있으니 List
    // 6. Livedata

    @Query("SELECT * FROM Information")
    LiveData<List<Information>> getAll_infor();
    // 6. Livedata

    @Insert
    void insert_todo(Todo todo);

    @Insert
    void insert_infor(Information information);

    @Query("UPDATE TODO SET title=:title WHERE id=:id")
    void update_todoitem(String title, Integer id);

    @Query("DELETE FROM TODO WHERE id=:id")
    void delete_todoitem(Integer id);
}
