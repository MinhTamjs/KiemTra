package com.example.foodorder.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.foodorder.Model.SanPhamModel;
import com.example.foodorder.database.DbHelper;

import java.util.ArrayList;

public class ProductDAO {
    private final DbHelper dbHelper;

    public ProductDAO(Context context){
        this.dbHelper = new DbHelper(context);
    }

    public ArrayList<SanPhamModel> getListProduct(){
        ArrayList<SanPhamModel> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM FOODORDER", null);
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    list.add(new SanPhamModel(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getBlob(3)));
                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("MainActivity", "List size: " + list.size());
        }finally {
            database.endTransaction();
        }
        return list;
    }
    public void deleteProduct(int productId) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("FOODORDER", "MASP = ?", new String[]{String.valueOf(productId)});
        database.close();
    }
    public long insertProduct(String productName, String productPrice) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSP", productName);
        values.put("GIASP", productPrice);

        long newRowId = database.insert("FOODORDER", null, values);
        database.close();
        return newRowId;
    }

}
