package com.example.raund;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.raund.model.RegisClass;
import com.example.raund.retrofit.PortalClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisActivity extends AppCompatActivity {
//    private Button BtnSignUp;
    EditText editNim;
    EditText editNama;
    EditText editEmail;
    EditText editTelepon;
    EditText editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        //Button
//        BtnSignUp = findViewById(R.id.BtnSignUp);
//        BtnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LoginActivity();
//            }
//        });
    }
//    public void LoginActivity() {
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//    }
    public void Register(View view) {

        editNim = findViewById(R.id.nimRegis);
        editNama = findViewById(R.id.namaRegis);
        editEmail = findViewById(R.id.emailRegis);
        editTelepon = findViewById(R.id.telRegis);
        editPassword = findViewById(R.id.passRegis);

        String nim = editNim.getText().toString();
        String nama = editNama.getText().toString();
        String email = editEmail.getText().toString();
        String telepon = editTelepon.getText().toString();
        String password = editPassword.getText().toString();

        //Buat Object Client Retrofit
        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        Call<RegisClass> call = client.registerResponse(nim, nama, email, telepon, password);
        call.enqueue(new Callback<RegisClass>() {
            @Override
            public void onResponse(Call<RegisClass> call, Response<RegisClass> response) {
                if(response.body() != null && response.isSuccessful()) {
                    Toast.makeText(RegisActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegisClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
