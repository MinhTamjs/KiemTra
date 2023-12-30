package com.example.foodorder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.Adapter.CustomAlertDialog;
import com.example.foodorder.Adapter.ProductAdapter;
import com.example.foodorder.Adapter.SwipeToDeleteCallback;
import com.example.foodorder.DAO.ProductDAO;
import com.example.foodorder.Model.SanPhamModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SanPhamModel sanPhamModel;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewProduct);
        ProductDAO productDAO = new ProductDAO(this);
        ArrayList<SanPhamModel> list = productDAO.getListProduct();

        ProductAdapter productAdapter = new ProductAdapter(this, list, new ProductAdapter.OnProductDeleteListener() {
            @Override
            public void onProductDelete(int position) {
                // Handle the delete action, e.g., remove the item from the database
                productDAO.deleteProduct(list.get(position).getMasp());
            }
        });

        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(productAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        CustomAlertDialog.show(this, new CustomAlertDialog.OnSaveClickListener() {
            @Override
            public void onSaveClick(String productName, String productPrice) {
                // Gọi phương thức để thêm sản phẩm vào database
                long newRowId = productDAO.insertProduct(productName, productPrice);

                // Kiểm tra xem có dòng nào được thêm thành công hay không
                if (newRowId != -1) {
                    // Thêm thành công
                    // Cập nhật RecyclerView hoặc làm gì đó khác theo nhu cầu của bạn
                } else {
                    // Thêm không thành công
                }
            }
        }, new CustomAlertDialog.OnClearClickListener() {
            @Override
            public void onClearClick() {
                // Xử lý logic khi người dùng nhấn Clear
                // Xóa nội dung các EditText hoặc thực hiện hành động khác
            }
        });

    }
}
