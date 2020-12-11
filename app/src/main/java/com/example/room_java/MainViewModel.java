package com.example.room_java;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.Date;
import java.util.List;

// 9. context가 필요한 경우엔 AndroidViewModel을 상속받아 생성자를 생성하면 됨
public class MainViewModel extends AndroidViewModel {
    private AppDatabase db;
    private LiveData<List<Todo>> todos;
    private String editTodo;
    private Date date = new Date();
    private Todo todo;

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getEditTodo() { return editTodo; }
    public void setEditTodo(String editTodo) { this.editTodo = editTodo; }
    public LiveData<List<Todo>> getTodos() { return todos; }
    public void setTodos(LiveData<List<Todo>> todos) { this.todos = todos; }
    public Todo getTodo() { return todo; }
    public void setTodo(Todo todo) { this.todo = todo; }
    // 13. viewModel에서 변경되는 값을 xml이 바로 받게하기 위해서 수정을 조금 해줘야함
    // 아니면 계속 getAll_todo()를 호출해 무한루프가 돈다. 이 todos를 xml이 관찰하여 변경함

    public MainViewModel(@NonNull Application application) {
        super(application);


        //final AppDatabase 
                db = Room.databaseBuilder(application, AppDatabase.class, "todo-db")
                //.allowMainThreadQueries()
                // db는 background에서 처리하지 않으면 에러, mainthread에서 처리 가능하게 해줌
                .build();
        // 5. 해당 db명으로 데이터베이스 객체를 생성 / 9. MainViewModel로 옮기고
        // , this를 application으로 변경

        todos = getAll_todo();
        // 13. getAll_todo() 호출
    }
    public LiveData<List<Todo>> getAll_todo(){
        return db.todoDao().getAll_todo();
    }   // 9. UI와 로직 분리를 위해 생성

    public void insert_todo(){
        todo = new Todo(editTodo, date);
        Log.d("editTodo : ", editTodo);
        Log.d("Todo : ", todo.toString());
        new InsertAsyncTask(db.todoDao()).execute(todo);
        // 8. 비동기처리를 위해 해당 클래스 실행   // 9. MainViewModel로 옮기고 소스변경
    }   // 9. UI와 로직 분리를 위해 생성


    private static class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao todoDao;
        public InsertAsyncTask(TodoDao todoDao) {
            this.todoDao = todoDao;
        }
        @Override
        protected Void doInBackground(Todo... todos) {
            todoDao.insert_todo(todos[0]);
            return null;
        }
    }   // 8. 비동기처리 // 9. MainViewModel로 옮김
}
