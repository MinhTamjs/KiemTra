package com.example.foodorder.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodorder.R;

public class CustomAlertDialog {

    public interface OnSaveClickListener {
        void onSaveClick(String productName, String productPrice);
    }

    public interface OnClearClickListener {
        void onClearClick();
    }

    public static void show(Context context, OnSaveClickListener onSaveClickListener, OnClearClickListener onClearClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog, null);
        builder.setView(view);

        EditText editTextProductName = view.findViewById(R.id.editTextProductName);
        EditText editTextProductPrice = view.findViewById(R.id.editTextProductPrice);
        Button buttonSave = view.findViewById(R.id.buttonSave);
        Button buttonClear = view.findViewById(R.id.buttonClear);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = editTextProductName.getText().toString();
                String productPrice = editTextProductPrice.getText().toString();
                onSaveClickListener.onSaveClick(productName, productPrice);
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearClickListener.onClearClick();
            }
        });

        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
