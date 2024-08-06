package com.example.assignment_a.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    Call<ProductResponse> getProducts();
}
