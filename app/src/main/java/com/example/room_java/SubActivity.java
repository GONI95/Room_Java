package com.example.room_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.room_java.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySubBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sub);
        //binding.setLifecycleOwner(this);

        int position = getIntent().getIntExtra("position", 5);
        Log.d("position : ", String.valueOf(position));

        MainViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider
                .AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);

        //binding.setViewModel(viewModel);
        viewModel.get_infor(position).observe(this, informations -> {
            binding.informationText.setText(informations.toString());
            Log.d("get_infor : ", informations.toString());
        });

        binding.informationText.setOnLongClickListener(v -> {
            binding.informationEdit.setText(binding.informationText.getText().toString());

            binding.inforTextScroll.setVisibility(View.GONE);
            binding.inforEditScroll.setVisibility(View.VISIBLE);

            return false;
        });

        binding.inforUpdatebutton.setOnClickListener(v -> {
            viewModel.insert_infor(new Information(position, binding.informationEdit.getText().toString()));

            binding.inforTextScroll.setVisibility(View.VISIBLE);
            binding.inforEditScroll.setVisibility(View.GONE);
        });
    }
}