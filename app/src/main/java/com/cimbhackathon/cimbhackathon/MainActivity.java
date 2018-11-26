package com.cimbhackathon.cimbhackathon;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private  FirebaseAuth auth;
    private Button btnSignOut ,btnCapture;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView nv;
    private ImageView imageView;
//    private FirebaseStorage
//    private StorageReference
    private static final int CAMERA_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        drawer = (DrawerLayout) findViewById(R.id.activity_main);
        toggle = new ActionBarDrawerToggle(this , drawer ,R.string.Open, R.string.Close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.account:
                        Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                    case R.id.balance:
                        Toast.makeText(MainActivity.this, "My Balance",Toast.LENGTH_SHORT).show();
                    case R.id.paymentHistory:

                    case R.id.signOut:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this , LoginActivity.class));
                    default:
                        return true;

                }
            }
        });




    }
//    protected void onActivityResult(int requestCode,int resultCode ,Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
//
//        if(requestCode==CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
//            Uri uri = data.getData();
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
