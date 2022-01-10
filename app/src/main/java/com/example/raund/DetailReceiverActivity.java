package com.example.raund;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class DetailReceiverActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button nextreceiver_btn;
    private GoogleMap mMap;
    SearchView searchLocReceiver;
    EditText editTxtLocReceiver;
    EditText editTxtLongReceiver;
    EditText editTxtLatReceiver;
    EditText editTxtNamaReceiver;
    EditText editTxtNoReceiver;
    EditText editTextdatePergi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_receiver);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences preferences = getSharedPreferences(
                "com.example.raund.PREFS", MODE_PRIVATE);
        String token = preferences.getString("ACCESS_TOKEN", "");
//        String longSender = preferences.getString("LONG_SENDER", "");
//        String latSender = preferences.getString("LAT_SENDER", "");
//        Toast.makeText(getApplicationContext(), longSender, Toast.LENGTH_SHORT).show();

        nextreceiver_btn = findViewById(R.id.nextBtn);
        nextreceiver_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseTravelPaketActivity();
            }
        });

        searchLocReceiver = findViewById(R.id.searchLocReceiver);
        editTxtLocReceiver = findViewById(R.id.editTxtLocReceiver);
        editTxtLatReceiver = findViewById(R.id.longReceiver);
        editTxtLongReceiver = findViewById(R.id.LatReceiver);
        editTxtNamaReceiver = findViewById(R.id.nama_receiver);
        editTxtNoReceiver = findViewById(R.id.no_receiver);
        editTextdatePergi = findViewById(R.id.datePergi);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        searchLocReceiver.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location = searchLocReceiver.getQuery().toString();

                List<Address> addressList = null;
                if(addressList != null || !location.equals("") ){
                    Geocoder geocoder = new Geocoder(DetailReceiverActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    editTxtLocReceiver.setText(Html.fromHtml("<font></font>") + addressList.get(0).getSubAdminArea());

                    Address address = addressList.get(0);
                    editTxtLatReceiver.setText(String.valueOf(address.getLatitude()));
                    editTxtLongReceiver.setText(String.valueOf(address.getLongitude()));
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Lokasi tujuan kamu"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,8));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);
    }
    public void ChooseTravelPaketActivity(){
        //get
        String locReceiver = editTxtLocReceiver.getText().toString();
        String longReceiver = editTxtLongReceiver.getText().toString();
        String latReceiver = editTxtLatReceiver.getText().toString();
        String namaReceiver = editTxtNamaReceiver.getText().toString();
        String noReceiver = editTxtNoReceiver.getText().toString();
        String datePergi = editTextdatePergi.getText().toString();

        SharedPreferences preferences = getSharedPreferences(
                "com.example.raund.PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("LOC_RECEIVER", locReceiver);
        editor.putString("LONG_RECEIVER", longReceiver);
        editor.putString("LAT_RECEIVER", latReceiver);
        editor.putString("NAMA_RECEIVER", namaReceiver);
        editor.putString("NO_RECEIVER", noReceiver);
        editor.putString("DATE_PERGI", datePergi);
        editor.apply();

        Intent intent = new Intent(this, ChooseTravelPaketActivity.class);
        startActivity(intent);
    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-0.9152784, 100.455757);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Unand"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
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