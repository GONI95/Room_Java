package com.example.room_java;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_java.databinding.ActivityMainBinding;
import com.example.room_java.databinding.CustomRecyclerviewItemBinding;

import java.util.List;

// 15. 리사이클러뷰는 데이터 목록을 아이템 단위의 뷰로 구성해 화면에 표시하기 위해 어댑터가 필요
// 리아시클러뷰에선 아이템 뷰가 나열되는 형태를 관리하기 위한 요소를 제공하는 이를 레이아웃 매니저라고 한다
// 레이아웃매니저가 제공하는 레이아웃 형태로 어댑터를 통해 만들어진 각 아이템 뷰는 뷰홀더 객체에 저장되어
//화면에 표시된다

//https://velog.io/@phoebe/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B0%94%EC%9D%B8%EB%94%A9-Recyclerview
//https://developer.android.com/topic/libraries/data-binding/generated-binding?hl=ko
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    MainViewModel viewModel;
    List<Todo> todos;

    public CustomAdapter(Context context,
                         MainViewModel viewModel,
                         List<Todo> todos) {
        this.context = context;
        this.viewModel = viewModel;
        this.todos = todos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     /*
      CustomRecyclerviewItemBinding binding = CustomRecyclerviewItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.custom_recyclerview_item, parent, false);
        return new ViewHolder(itemView);
      */

        CustomRecyclerviewItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.custom_recyclerview_item, parent, false);
        return new ViewHolder(binding);
        // 16. 데이터바인딩 - 커스텀 리사이클뷰 xml을 데이터바인딩으로 설정

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.binding.tvRoomId.setText(todos.get(position).getId().toString()+"번");
        //holder.binding.tvRoomDate.setText("날짜: "+todos.get(position).getDate().toString());
        //holder.binding.tvRoomTitle.setText((CharSequence) "제목: " + todos.get(position).getTitle());

        holder.bind(todos.get(position));
        // 16. 홀더에 정의된 bind 함수에 todo형태의 리스트를 포지션별로 넘김

        // 요소들 출력

        Log.d("todo = ", todos.get(position).toString());

        holder.itemView.setOnCreateContextMenuListener((menu, v, menuInfo) -> {
            menu.add("삭제").setOnMenuItemClickListener(item -> {
                viewModel.delete_todoItem(todos.get(position).getId());
                return false;
            });
            menu.add("편집").setOnMenuItemClickListener(item -> {
                AlertDialogActivity dialog = new AlertDialogActivity(context, todos, position, viewModel);
                dialog.show();
                return false;
            });
        });
    }

    @Override
    public int getItemCount() {
        return (null != todos ? todos.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CustomRecyclerviewItemBinding binding;
        //private TextView tv_id;
       // private TextView tv_title;
       // private TextView tv_date;

        public ViewHolder(CustomRecyclerviewItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;

            //tv_id = itemView.findViewById(R.id.tv_room_id);
           // tv_title = itemView.findViewById(R.id.tv_room_title);
           // tv_date = itemView.findViewById(R.id.tv_room_date);
        }

        public void bind(Todo todo){
            binding.setTodo(todo);
            // xml에 정의된 todo에 받은 todo형태의 데이터를 넘김
            //binding.executePendingBindings();
        }
    }
}

