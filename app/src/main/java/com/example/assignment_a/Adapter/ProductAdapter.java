package com.example.assignment_a.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.assignment_a.R;
import com.example.assignment_a.modal.Product;
import com.example.assignment_a.Activities.thirdActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_TITLE = 0;
    private static final int VIEW_TYPE_PRODUCT = 1;

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_TITLE;
        } else {
            return VIEW_TYPE_PRODUCT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_TITLE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
            return new TitleViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_TITLE) {
//            No need of Binding (Title item)
        } else {
            Product product = productList.get(position - 1);
            ProductViewHolder productViewHolder = (ProductViewHolder) holder;
            productViewHolder.productTitle.setText(product.getTitle());
            productViewHolder.productDescription.setText(product.getDescription());

            Glide.with(context).load(product.getThumbnail())
                    .apply(new RequestOptions().placeholder(R.drawable.loading)).error(R.drawable.loading)
                    .into(productViewHolder.productImage);

            // Set click listener for navigating to Third activity
            productViewHolder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, thirdActivity.class);
                intent.putExtra("productTitle", product.getTitle());
                intent.putExtra("productDescription", product.getDescription());
                intent.putExtra("productThumbnail", product.getThumbnail());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size() + 1; // +1 for the title item
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productTitle;
        TextView productDescription;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productTitle = itemView.findViewById(R.id.productTitle);
            productDescription = itemView.findViewById(R.id.productDescription);
        }
    }
}
