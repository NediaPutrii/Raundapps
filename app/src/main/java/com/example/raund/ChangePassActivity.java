package com.example.raund;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.raund.model.MyPasswordResponse;
import com.example.raund.retrofit.PortalClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePassActivity extends AppCompatActivity {
    Button BtnSavePass;
    EditText editPasswordlama, editPasswordBaru1, editPasswordBaru2;
    public String passwordlamaC, passwordbaru1C, passwordbaru2C;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.raund.PREFS", Context.MODE_PRIVATE
        );
        String accessToken = preferences.getString("ACCESS_TOKEN","");
        String passwordlama = preferences.getString("PASSWORD","");

        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        editPasswordlama = (EditText) findViewById(R.id.passwordlama);
        editPasswordBaru1= (EditText) findViewById(R.id.passwordbaru1);
        editPasswordBaru2 = (EditText) findViewById(R.id.passwordbaru2);

//        Call<MyPasswordResponse> call = client.getMyPassword("Bearer" + accessToken, passwordlama, "", "");
//        call.enqueue(new Callback<MyPasswordResponse>(){
//
//                         @Override
//                         public void onResponse(Call<MyPasswordResponse> call, Response<MyPasswordResponse> response) {
//                             MyPasswordResponse changepassword = response.body();
//
//
//                             if(changepassword != null && response.isSuccessful())
//                             {
//
//                             }
//
//                         }
//
//                         @Override
//                         public void onFailure(Call<MyPasswordResponse> call, Throwable t) {
//
//                         }
//                     });


        //Button
        BtnSavePass = findViewById(R.id.BtnSavePass);
        BtnSavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordlamaC = editPasswordlama.getText().toString();
                passwordbaru1C = editPasswordBaru1.getText().toString();
                passwordbaru2C = editPasswordBaru2.getText().toString();
                updatePassword();
                SettingActivity();

            }
        });
    }

    public void updatePassword()
    {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.raund.PREFS", Context.MODE_PRIVATE
        );
        String accessToken = preferences.getString("ACCESS_TOKEN","");

        //Buat Object Client Retrofit
        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        Call<MyPasswordResponse> call = client.getMyPassword("Bearer" + accessToken, passwordlamaC, passwordbaru1C, passwordbaru2C);
        call.enqueue(new Callback<MyPasswordResponse>(){

            @Override
            public void onResponse(Call<MyPasswordResponse> call, Response<MyPasswordResponse> response) {
//                MyPasswordResponse changepassword = response.body();

                if(response.body() != null && response.isSuccessful())
                {
                    Intent intent = new Intent(ChangePassActivity.this, SettingActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Mantap Sudah berubah yaa", Toast.LENGTH_SHORT).show();

                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MyPasswordResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void SettingActivity(){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

}
