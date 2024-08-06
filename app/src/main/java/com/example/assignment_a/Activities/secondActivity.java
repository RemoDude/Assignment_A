package com.example.assignment_a.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assignment_a.Adapter.ProductAdapter;
import com.example.assignment_a.R;
import com.example.assignment_a.Retrofit.ApiService;
import com.example.assignment_a.Retrofit.ProductResponse;
import com.example.assignment_a.Retrofit.RetrofitClient;
import com.example.assignment_a.databinding.ActivitySecondBinding;
import com.example.assignment_a.modal.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class secondActivity extends AppCompatActivity {
    ActivitySecondBinding binding;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.tool_color));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // Enable the home button
        getSupportActionBar().setDisplayShowHomeEnabled(true); // Enable the home button

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        binding.recyclerView.setAdapter(adapter);

        fetchProducts();
    }

    private void fetchProducts() {
        binding.loading.setVisibility(View.VISIBLE);
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<ProductResponse> call = apiService.getProducts();
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProductResponse> call, @NonNull Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList.addAll(response.body().getProducts());
                    binding.loading.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(secondActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductResponse> call, @NonNull Throwable t) {
                Toast.makeText(secondActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}