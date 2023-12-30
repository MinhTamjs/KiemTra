package com.example.foodorder.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.Model.SanPhamModel;
import com.example.foodorder.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<SanPhamModel> productList;
    private static OnProductDeleteListener deleteListener;
    private Context context;
    public interface OnProductDeleteListener {
        void onProductDelete(int position);
    }

    public ProductAdapter(Context context, List<SanPhamModel> productList, OnProductDeleteListener deleteListener) {
        this.context = context;
        this.productList = productList;
        ProductAdapter.deleteListener = deleteListener;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        SanPhamModel product = productList.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);


        // Gán dữ liệu cho các thành phần trong item
        holder.imageViewProduct.setImageBitmap(bitmap);
        holder.textViewProductName.setText(product.getTesp());
        holder.textViewProductPrice.setText(product.getGiasp());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewProductPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Gọi phương thức xóa từ Interface
                        deleteListener.onProductDelete(position);
                    }
                }
            });
        }
    }
    public void removeProduct(int position) {
        productList.remove(position);
        notifyItemRemoved(position);
    }




}
