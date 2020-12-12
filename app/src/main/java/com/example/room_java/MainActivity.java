package com.example.room_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.room_java.databinding.ActivityMainBinding;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

// db 파일 위치 : view -> tool windows -> device file explorer -> data -> data -> 패키지 명
public class MainActivity extends AppCompatActivity {
    private EditText edit;
    private TextView tv;
    private Date date = new Date();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // 11. databind 사용을 위해 설정 (layout 설정으로 인해 컴파일러가 ActivityMainBinding을 자동 생성)
        binding.setLifecycleOwner(this);
        // 12. livedata를 xml이 관찰하다가 변경하도록 할 수 있다.

        Log.d("Date : ", date.toString());

        MainViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);
        //MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);   // 일반 ViewModel 사용시
        // 10. ViewModel 객체 생성

        binding.setViewModel(viewModel);
        // 12. viewModel이 binding 객체에 설정된 xml로 전달됨, xml에 <data> 선언해줘야함

        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        // 15. 리사이클러뷰 사용을 위한 코드
        viewModel.getAll_todo().observe(this, todos -> {
            adapter = new CustomAdapter(this, viewModel, todos);
            // 어답터 객체 생성
            binding.recyclerView.setAdapter(adapter);
            // 리사이클러 뷰에 어답터 설정
        });

        /*

         viewModel.getAll_todo().observe(this, todos -> {
            binding.todoTextView.setText(todos.toString());    // 데이터를 표시하는 코드
        });
        // 7. this : lifecycle을 주관하는 Activity, Todo객체, 관찰하다 UI를 갱신해줌
        // 14. 해당 코드가 하는 작업을 viewModel에서 databinding으로 처리하기 때문에 필요가 없음

            binding.todoButton.setOnClickListener(v -> {
            viewModel.insert_todo(new Todo(binding.todoEditText.getText().toString(), date));

           InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
           inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
           // 버튼 클릭시 입력창 내려감
       });


         */
    }
}