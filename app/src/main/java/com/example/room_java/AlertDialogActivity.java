package com.example.room_java;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import kotlinx.coroutines.Dispatchers;

//https://chuumong.github.io/android/2017/01/16/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4
//https://www.slideshare.net/VladislavErmolin/dialogs-in-android-mvvm-14112019
//https://blog.yena.io/studynote/2019/03/27/Android-MVVM-AAC-2.html


//https://webnautes.tistory.com/1094
public class AlertDialogActivity extends Dialog {
    Context context;
    List<Todo> todos;
    int position;
    MainViewModel viewModel;

    public AlertDialogActivity(@NonNull Context context, List<Todo> todos, int position, MainViewModel viewModel) {
        super(context);
        this.context = context;
        this.todos = todos;
        this.position = position;
        this.viewModel = viewModel;
    }

    @Override
    public void show() {

        View dialogView = LayoutInflater.from(context)
                .inflate(R.layout.custom_dialog, null);

        EditText dialogText = dialogView.findViewById(R.id.dialog_ed);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setTitle("memo_table");
        builder.setMessage("데이터를 변경합니다.");
        builder.setView(dialogView)
                .setPositiveButton("확인", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.update_todoItem(dialogText.getText().toString(), todos.get(position).getId());
                    }
                })
                .setNegativeButton("취소", (dialog, which) -> {
                }).show();
    }
}
