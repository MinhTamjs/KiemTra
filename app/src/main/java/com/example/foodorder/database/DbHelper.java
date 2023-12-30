package com.example.foodorder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.foodorder.R; // Đường dẫn tới ảnh, điều này phụ thuộc vào cấu trúc project của bạn

import java.io.ByteArrayOutputStream;

public class DbHelper extends SQLiteOpenHelper {
    private Context context;

    public DbHelper(Context context) {
        super(context, "FoodOrderDatabase", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE FOODORDER(MASP INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TENSP TEXT, GIASP TEXT, IMAGE BLOB)";
        sqLiteDatabase.execSQL(sql);


        // Thêm sẵn vài dữ liệu
        addSampleData(sqLiteDatabase, "Khoai tay hầm", "20000", R.drawable.khoai_tay_ham);
        addSampleData(sqLiteDatabase, "Cà chua", "15000", R.drawable.ca_chua);
        addSampleData(sqLiteDatabase, "Cháo", "10000", R.drawable.chao);
    }

    private void addSampleData(SQLiteDatabase sqLiteDatabase, String tenSP, String giaSP, int imageResource) {
        // Chuyển đổi ảnh từ resource thành mảng byte
        byte[] imageData = getByteArrayFromDrawable(context, imageResource);

        // Tạo ContentValues để chèn dữ liệu vào bảng
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENSP", tenSP);
        contentValues.put("GIASP", giaSP);
        contentValues.put("IMAGE", imageData);

        // Chèn dữ liệu vào bảng
        sqLiteDatabase.insert("FOODORDER", null, contentValues);
    }

    private byte[] getByteArrayFromDrawable(Context context, int imageResource) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageResource);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newVersion) {
        if (olderVersion != newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE FOODORDER");
            onCreate(sqLiteDatabase);
        }
    }
}
