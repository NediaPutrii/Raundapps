package com.example.raund;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.raund.model.AuthClass;
import com.example.raund.model.AuthData;
import com.example.raund.retrofit.PortalClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    TextView BtnSignUp;
    EditText editEmail;
    EditText editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();

        //Button
        BtnSignUp = findViewById(R.id.idCreateAccTxt);
        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisActivity();
            }
        });

    }
    public void RegisActivity() {
        Intent intent = new Intent(this, RegisActivity.class);
        startActivity(intent);
    }

    public void checkLogin(View view) {
        editEmail = findViewById(R.id.inputEmailLogin);
        editPassword = findViewById(R.id.inputPassLogin);

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        //Buat Object Client Retrofit
        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        Call<AuthClass> call = client.checkLogiin(email, password);

        call.enqueue(new Callback<AuthClass>() {
            @Override
            public void onResponse(Call<AuthClass> call, Response<AuthClass> response) {
                AuthClass authClass = response.body();
                if(authClass != null && response.isSuccessful()) {
                    AuthData data = authClass.getData();
                    String token = data.getToken();
                    String nama = data.getNama();
                    String email = data.getEmail();
                    String nim = data.getNim();
                    String no_telepon = data.getNoTelepon();


                    SharedPreferences preferences =
                            getSharedPreferences("com.example.raund.PREFS", MODE_PRIVATE);

                        SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("ACCESS_TOKEN",token);
                    editor.putString("NAMA", nama);
                    editor.putString("NIM", nim);
                    editor.putString("EMAIL",email);
                    editor.putString("NO_TELEPON",no_telepon);
//
                    editor.apply();

                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
