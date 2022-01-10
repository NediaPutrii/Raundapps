package com.example.raund;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NoSaveAddressActivity extends AppCompatActivity {
    Button btnAddAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_save_address);
        //btnAddAddress
        btnAddAddress = findViewById(R.id.btnAddAddress);
        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SavedAddressActivity();
            }
        });

    }
    public void SavedAddressActivity(){
        Intent intent = new Intent(this, SavedAddressActivity.class);
        startActivity(intent);
    }
}
