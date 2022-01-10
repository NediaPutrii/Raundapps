package com.example.raund;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.raund.model.AuthClass;
import com.example.raund.model.History;
import com.example.raund.model.HistoryItem;
import com.example.raund.model.MyEditResponse;
import com.example.raund.model.MySettingResponse;
import com.example.raund.model.OnGoing;
import com.example.raund.model.OngoingItem;
import com.example.raund.model.OngoingResponse;
import com.example.raund.model.RegisClass;
import com.example.raund.model.SettingItem;
import com.example.raund.model.SettingResponse;
import com.example.raund.retrofit.PortalClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    private Button BtnEditProfile;
//    private TextView SavedAddressTextView;
    private TextView ChangePassTextView;
    private TextView LogOutTextView;
    public  String namaa;
    public  String nimm;
    public String emaill;
    public  String teleponn;
    TextView editEmail;
    TextView editNama;
    TextView editNim;
    @SuppressLint("WrongViewCast")
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        editNama = findViewById(R.id.namaSetting);
        editNim = findViewById(R.id.nimSetting);
        editEmail = findViewById(R.id.emailSetting);


//        editNim.setText().toString();
//       editNama.getText().toString();
//        editEmail.getText().toString();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.raund.PREFS", Context.MODE_PRIVATE
        );
        String accessToken = preferences.getString("ACCESS_TOKEN","");
//        SharedPreferences sp = getSharedPreferences("Key", Activity.MODE_PRIVATE);
//        String accessNama = preferences.getString("NAMA", "");
//        String accessNim = preferences.getString("NIM", "");
//        String accessEmail = preferences.getString("EMAIL", "");

//
//        String accessNama = preferences.getString("NAMA","");
//        String accessNim = preferences.getString("NIM","");
//        String accessEmail = preferences.getString("EMAIL","");

        //Minta data travel ongoing
        //Buat Object Client Retrofit
        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);
        Call<MySettingResponse> call = client.getMySettings("Bearer" + accessToken );
        call.enqueue(new Callback<MySettingResponse>() {
            

            @Override
            public void onResponse(Call<MySettingResponse> call, Response<MySettingResponse> response) {


                MySettingResponse settingResponse = response.body();
//                Log.d("pesan",t.getMessage());




                if(settingResponse != null && response.isSuccessful()){
                     namaa = settingResponse.getNama();
                    nimm = settingResponse.getNim();
                    emaill = settingResponse.getEmail();
                    teleponn = settingResponse.getNoTelepon();
//                    List<SettingItem> ListSettingItem = settingResponse.getSettings();
//                    for (SettingItem his : ListSettingItem) {
//                        String namaa = his.getNama();
//                        String nimm = his.getNim();
//                        String emaill = his.getEmail();


                        // set reference to the text view
                        editNama = (TextView) findViewById(R.id.namaSetting);
                        editNim = (TextView) findViewById(R.id.nimSetting);
                        editEmail = (TextView) findViewById(R.id.emailSetting);
                        // set the string from sp as text of the textview
                        editNama.setText(namaa);
                        editNim.setText(nimm);
                        editEmail.setText(emaill);

//                    }

//                    ListSettingItem.ge
                    // get the saved string from shared preferences
//                    String name1 = preferences.getString("NAMA", "");
//                    String nim1 = preferences.getString("NIM", "");
//                    String email1 = preferences.getString("EMAIL", "");

                    // set reference to the text view
//                    editNama = (TextView) findViewById(R.id.namaSetting);
//                    editNim = (TextView) findViewById(R.id.nimSetting);
//                    editEmail = (TextView) findViewById(R.id.emailSetting);
                    // set the string from sp as text of the textview
//                    editNama.setText(namaa);
//                    editNim.setText(nimm);
//                    editEmail.setText(emaill);


//                    editNama.setText();
//                    editNim.setText(accessNim.toString());
//                    editEmail.setText(accessEmail.toString());




                }else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }


                    }




            @Override
            public void onFailure(Call<MySettingResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        //Button
        BtnEditProfile = findViewById(R.id.BtnEditProfile);
        BtnEditProfile.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingActivity.this, ProfileEditActivity.class);
                intent.putExtra("nama", namaa);
                intent.putExtra("nim",nimm);
                intent.putExtra("email",emaill);
                intent.putExtra("telepon",teleponn);
                startActivity(intent);
                ProfileEditActivity();
            }
        });

        //Saved Address Text View
//        SavedAddressTextView = findViewById(R.id.SavedAddressTextView);
//        SavedAddressTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NoSaveAddressActivity();
//            }
//        });

        //Change Password Text View
        ChangePassTextView = findViewById(R.id.ChangePassTextView);
        ChangePassTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePassActivity();
            }
        });

        //Logout
        LogOutTextView = findViewById(R.id.LogOutTextView);
        LogOutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogOut();
                Toast.makeText(getApplicationContext(), "ANDA SUDAH KELUAR", Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void ProfileEditActivity(){
        Intent intent = new Intent(this, ProfileEditActivity.class);
        startActivity(intent);
    }
    public void NoSaveAddressActivity(){
        Intent intent = new Intent(this, NoSaveAddressActivity.class);
        startActivity(intent);
    }
    public void ChangePassActivity(){
        Intent intent = new Intent(this, ChangePassActivity.class);
        startActivity(intent);
    }

    public void LogOut(){

        SharedPreferences preferences =getSharedPreferences("loginPrefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();


//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//
//        alertDialogBuilder.setTitle("Anu");
//        alertDialogBuilder
//                .setMessage("Anda yakin ingin logout?")
//                .setCancelable(false)
//                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                SharedPreferences preferences =getSharedPreferences("loginPrefs",Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = preferences.edit();
//                                editor.clear();
//                                editor.apply();
//                                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
//                                startActivity(intent);
//                                finish();
//
//                            }
//                        });


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
