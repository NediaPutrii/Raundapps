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

import com.example.raund.model.MyEditResponse;
import com.example.raund.retrofit.PortalClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileEditActivity extends AppCompatActivity {

    private Button BtnSaveProfile;
    EditText editEmail;
    EditText editNama;
    EditText editNim;
    EditText editTelepon;
    String ynama,ynim,yemail,ytelepon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        editNama = findViewById(R.id.editNama);
//        editNim = findViewById(R.id.editNim);
//        editEmail = findViewById(R.id.editEmail);
//        editTelepon = findViewById(R.id.editTelepon);

        // set reference to the text view

//        editNama = (EditText) findViewById(R.id.editNama);
//        editNim = (EditText) findViewById(R.id.editNim);
//        editEmail = (EditText) findViewById(R.id.editEmail);
//        editTelepon = (EditText) findViewById(R.id.editTelepon);

//        String nim = editNim.getText().toString();
//        String nama = editNama.getText().toString();
//        String email = editEmail.getText().toString();
//        String telepon = editTelepon.getText().toString();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.raund.PREFS", Context.MODE_PRIVATE
        );
        String accessToken = preferences.getString("ACCESS_TOKEN","");

        Intent intent = getIntent();
        String name1 =  intent.getStringExtra("namaa");
        String nim1 =  intent.getStringExtra("nimm");
        String email1 =  intent.getStringExtra("emaill");
        String telepon1 =  intent.getStringExtra("telepon");


//        String name1 = preferences.getString("NAMA", "");
//        String nim1 = preferences.getString("NIM", "");
//        String email1 = preferences.getString("EMAIL", "");
//        String telepon1 = preferences.getString("NO_TELEPON", "");

//        Toast.makeText(getApplicationContext(),accessToken,Toast.LENGTH_SHORT).show();

        //Minta data travel ongoing
        //Buat Object Client Retrofit
        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

        Call<MyEditResponse> call = client.getMyEdits("Bearer" + accessToken, name1, nim1, email1, telepon1);
        call.enqueue(new Callback<MyEditResponse>() {
                         @Override
                         public void onResponse(Call<MyEditResponse> call, Response<MyEditResponse> response) {
                             MyEditResponse editProfileResponse = response.body();
                             String name1 = editProfileResponse.getNama();
                             String nim1 = editProfileResponse.getNim();
                             String email1 = editProfileResponse.getEmail();
                             String telepon1 = editProfileResponse.getNoTelepon();
//                             List<Editprofile> ListEditProfile = editProfileResponse.getEditprofile();



//                            listprof = response.body().getEditprofile().get(0).getNama();
//                             Toast.makeText(getApplicationContext(), name1, Toast.LENGTH_SHORT).show();





                             if(editProfileResponse != null && response.isSuccessful()){
//                                 List<Editprofile> ListEditProfile = editProfileResponse.getEditprofile();

//                                 AuthData data = authClass.getData();

//                                 Editprofile his = ListEditProfile.get;
//                                 Editprofile his = new Editprofile();
//
//                                 for (Editprofile prof : ListEditProfile) {
//
//                                     String namaa = prof.getNama();
//                                     String nimm = prof.getNim();
//                                     String emaill = prof.getEmail();
//                                     String no_teleponn = prof.getNoTelepon();
//
//
//                                     // set reference to the text view
//                                     editNama = (EditText) findViewById(R.id.editNama);
//                                     editNim = (EditText) findViewById(R.id.editNim);
//                                     editEmail = (EditText) findViewById(R.id.editEmail);
//                                     editTelepon = (EditText) findViewById(R.id.editTelepon);
//
//                                     // set the string from sp as text of the textview
//                                     editNama.setText(namaa);
//                                     editNim.setText(nimm);
//                                     editEmail.setText(emaill);
//                                     editTelepon.setText(no_teleponn);
//
//                                 }







//                                 String name1 = his.getNama();
//                                     String nim1 = his.getNim();
//                                     String email1 = his.getEmail();
//                                     String telepon1 = his.getNoTelepon();



                                 // get the saved string from shared preferences
//                                 String name1 = preferences.getString("NAMA", "");
//                                 String nim1 = preferences.getString("NIM", "");
//                                 String email1 = preferences.getString("EMAIL", "");
//                                 String telepon1 = preferences.getString("NO_TELEPON", "");


                                 editNama = (EditText) findViewById(R.id.editNama);
                                 editNim = (EditText) findViewById(R.id.editNim);
                                 editEmail = (EditText) findViewById(R.id.editEmail);
                                 editTelepon = (EditText) findViewById(R.id.editTelepon);


//                                  set the string from sp as text of the textview
                                 editNama.setText(name1);
                                 editNim.setText(nim1);
                                 editEmail.setText(email1);
                                 editTelepon.setText(telepon1);


//                    editNama.setText();
//                    editNim.setText(accessNim.toString());
//                    editEmail.setText(accessEmail.toString());

//                    Toast.makeText(SettingActivity.this, accessNama, Toast.LENGTH_SHORT).show();

//                                 Toast.makeText(ProfileEditActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

//                                 Toast.makeText(ProfileEditActivity.this, telepon1, Toast.LENGTH_SHORT).show();


                             }else{
                                 Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                             }


                         }

                         @Override
                         public void onFailure(Call<MyEditResponse> call, Throwable t) {

                         }
                     });



//        //Button
        BtnSaveProfile = findViewById(R.id.BtnSaveProfile);
        BtnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ynim = editNim.getText().toString();
                ynama = editNama.getText().toString();
                yemail = editEmail.getText().toString();
                ytelepon = editTelepon.getText().toString();

                updateData();
                SettingActivity();



            }
        });

    }
    public void updateData(){


        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                "com.example.raund.PREFS", Context.MODE_PRIVATE
        );
        String accessToken = preferences.getString("ACCESS_TOKEN","");

//        String name1 = preferences.getString("NAMA", "");
//        String nim1 = preferences.getString("NIM", "");
//        String email1 = preferences.getString("EMAIL", "");
//        String telepon1 = preferences.getString("NO_TELEPON", "");


        //Buat Object Client Retrofit
        String API_BASE_URL = "https://raund.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PortalClient client = retrofit.create(PortalClient.class);

//        EditProfileResponse edit = new EditProfileResponse(accessToken,name1,nim1,email1,telepon1);

        Call<MyEditResponse> call = client.getMyEdits("Bearer" + accessToken,ynama, ynim, yemail, ytelepon);
        call.enqueue(new Callback<MyEditResponse>() {
            @Override
            public void onResponse(Call<MyEditResponse> call, Response<MyEditResponse> response) {
                if(response.body() != null && response.isSuccessful()) {
//                    Toast.makeText(ProfileEditActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileEditActivity.this, SettingActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MyEditResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void  SettingActivity(){
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
