package com.example.assignment_a.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.assignment_a.R;
import com.example.assignment_a.databinding.ActivityThirdBinding;

import java.util.Objects;

public class thirdActivity extends AppCompatActivity {

    ActivityThirdBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.tool_color));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // Enable the home button
        getSupportActionBar().setDisplayShowHomeEnabled(true); // Enable the home button
        Intent intent = getIntent();
        String title = intent.getStringExtra("productTitle");
        String description = intent.getStringExtra("productDescription");
        String thumbnail = intent.getStringExtra("productThumbnail");

        binding.productTitle.setText(title);
        binding.productDescription.setText(description);
        Glide.with(this).load(thumbnail)
                .apply(new RequestOptions().placeholder(R.drawable.loading)).error(R.drawable.loading)
                .into(binding.productImage);


    }
}