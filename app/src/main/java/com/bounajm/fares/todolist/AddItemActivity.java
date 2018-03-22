package com.bounajm.fares.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.bounajm.fares.todolist.LoginActivity.connected;
import static com.bounajm.fares.todolist.LoginActivity.myRef;
import static com.bounajm.fares.todolist.LoginActivity.userID;

public class AddItemActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Date dueDate = new Date();
    private EditText nameEt, descriptionEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        nameEt = (EditText)findViewById(R.id.name_et);
        descriptionEt = (EditText)findViewById(R.id.description_et);

        Button datePickBtn = (Button)findViewById(R.id.selectDateBtn);

        final Calendar c2 = Calendar.getInstance();
        final DateFormat txtDate = DateFormat.getDateInstance();
        final DatePickerDialog.OnDateSetListener d2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c2.set(year, month, dayOfMonth);
                dueDate = c2.getTime();
                //txtDate.format(c2.getTime());
            }
        };

        datePickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddItemActivity.this, d2, c2.get(Calendar.YEAR), c2.get(Calendar.MONTH),
                        c2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private Marker markerPosition;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> dubaiLocaltion = geocoder.getFromLocationName("Dubai", 1);
            if(dubaiLocaltion.size() > 0){
                LatLng dubaiLoc = new LatLng(dubaiLocaltion.get(0).getLatitude(), dubaiLocaltion.get(0).getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dubaiLoc, 10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                if(markerPosition != null){
                    markerPosition.remove();
                }
                markerPosition = mMap.addMarker(new MarkerOptions().position(point).title("Location").draggable(true));
            }
        });
    }

    public void newEntrySave(View view){

        ListAndHistoryActivity.ListItem x = new ListAndHistoryActivity.ListItem();
        x.name = nameEt.getText().toString();
        x.description = descriptionEt.getText().toString();
        x.dueDate = dueDate;
        x.completed = false;
        if(markerPosition != null){
            x.locationSet = true;
            x.longitude = markerPosition.getPosition().longitude;
            x.latitude = markerPosition.getPosition().latitude;
        }else{
            x.locationSet = false;
        }


        if(connected){
            x.isOnline = true;
        }else{
            x.isOnline = false;
        }

        myRef.child(userID()).child("numbers").push().setValue(x);

        startActivity(new Intent(this, ListAndHistoryActivity.class));
        finish();
    }

}
