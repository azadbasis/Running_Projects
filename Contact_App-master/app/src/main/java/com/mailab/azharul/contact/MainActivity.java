package com.mailab.azharul.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText contactNameET;
    EditText contactPhoneET;
    Button operationBtn;
    boolean operation = false;
    int updateID;

    private ContactModel contactModel;
    private ContactDBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactNameET = (EditText) findViewById(R.id.contactNameETID);
        contactPhoneET = (EditText) findViewById(R.id.contactPhoneETID);
        operationBtn = (Button) findViewById(R.id.operationBtnID);
        dbManager = new ContactDBManager(getApplicationContext());

        updateID = getIntent().getIntExtra("id_key", 0);
        operation = getIntent().getBooleanExtra("update_key", false);

        if (operation) {
            contactModel = dbManager.getContact(updateID);
            contactNameET.setText(contactModel.getName());
            contactPhoneET.setText(contactModel.getPhoneNO());
            operationBtn.setText("Update");
        }

    }

    public void operation(View view) {


        if (operation) {
            String name = contactNameET.getText().toString();
            String phone = contactPhoneET.getText().toString();
            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Field is Empty", Toast.LENGTH_SHORT).show();
            } else {
                contactModel = new ContactModel(name, phone);
                dbManager.updateContact(updateID, contactModel);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }


        } else {
            // Save
            String name = contactNameET.getText().toString();
            String phone = contactPhoneET.getText().toString();
            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Field is Empty", Toast.LENGTH_SHORT).show();
            } else {

                contactModel = new ContactModel(name, phone);
                boolean inserted = dbManager.addContact(contactModel);

                if (inserted) {
                    Toast.makeText(getApplicationContext(), "Contact Added!", Toast.LENGTH_SHORT).show();
                }

                contactNameET.getText().clear();
                contactPhoneET.getText().clear();

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }

        }

    }

    public void skipStep(View view) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }
}
