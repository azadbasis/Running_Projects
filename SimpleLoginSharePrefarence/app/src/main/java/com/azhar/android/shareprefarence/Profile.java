package com.azhar.android.shareprefarence;


import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Profile extends Activity {
    EditText etName, etEmail, etPhone, etDesignation;
    DatabaseHelper dbHelper;

    // declare view
    ListView lvEmployees;
    // declare adapter
    CustomizedAdapter adapter;

    // datasource

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etDesignation = (EditText) findViewById(R.id.etDesignation);

        lvEmployees = (ListView) findViewById(R.id.lvEmployees);
        dbHelper = new DatabaseHelper(this);
    }

    public void save(View v) {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String designation = etDesignation.getText().toString();

        Student employee = new Student(name, email, phone, designation);
        Toast.makeText(getApplicationContext(), employee.toString(),
                Toast.LENGTH_LONG).show();

        long inserted = dbHelper.insertEmployee(employee);
        if (inserted >= 0) {
            Toast.makeText(getApplicationContext(), "Data inserted",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Data insertion failed...",
                    Toast.LENGTH_LONG).show();
        }

        clearText();
    }

    public void view(View v) {
        ArrayList<Student> employees = dbHelper.getAllEmployees();
        if (employees != null && employees.size() > 0) {
            adapter = new CustomizedAdapter(this, employees);
            lvEmployees.setAdapter(adapter);
        }


    }
    public void clearText() {
        etName.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etDesignation.requestFocus();
    }


}
