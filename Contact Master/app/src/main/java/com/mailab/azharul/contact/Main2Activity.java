package com.mailab.azharul.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView contactListView;

    ContactAdapter adapter;
    ArrayList<ContactModel> contactList = new ArrayList<>();
    ContactDBManager dbManager;
    ContactModel contactModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        contactListView = (ListView) findViewById(R.id.contactListViewID);

        this.makeList();

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("id_key", contactList.get(position).getId());
                startActivity(intent);
            }
        });

    }

    private void makeList() {

        dbManager = new ContactDBManager(getApplicationContext());
        contactList = dbManager.getAllContact();
        adapter = new ContactAdapter(getApplicationContext(), contactList);
        contactListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
