package com.mailab.azharul.contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    TextView contactDetailName;
    TextView contactDetailPhone;

    ContactDBManager dbManager;
    ContactModel contactModel;

    int id;
    String phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        contactDetailName = (TextView) findViewById(R.id.contactDetailNameID);
        contactDetailPhone = (TextView) findViewById(R.id.contactDetailPhoneID);

        id = getIntent().getIntExtra("id_key", 0);
        dbManager = new ContactDBManager(getApplicationContext());
        contactModel = dbManager.getContact(id);
        phoneNo = contactModel.getPhoneNO();
        contactDetailName.setText(contactModel.getName());
        contactDetailPhone.setText(phoneNo);

    }

    public void deleteContact(View view) {
        if (dbManager.deleteContact(id)) {
            Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
            startActivity(intent);
            finish();
        }
    }

    public void editContact(View view) {
        Intent intent = new Intent(Main3Activity.this, MainActivity.class);
        intent.putExtra("id_key", id);
        intent.putExtra("update_key", true);
        startActivity(intent);
        finish();
    }

    public void callNumber(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNo));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);

    }

    public void msgNumber(View view) {
        Intent msgIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
        startActivity(msgIntent);
    }

}
