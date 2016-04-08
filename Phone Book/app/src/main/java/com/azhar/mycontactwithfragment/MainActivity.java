package com.azhar.mycontactwithfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    Fragment fragmentForContact;
    FragmentManager manager;
    FragmentTransaction transaction;

    android.app.Fragment fragmentForListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Contact");

        fragmentForContact = new FragmentForContact();

        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.layout, fragmentForContact);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.contactList) {

            fragmentForListview = new FragmentForListview();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.layout, fragmentForListview);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }

        if (id == R.id.addContact) {

            fragmentForContact = new FragmentForContact();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.layout, fragmentForContact);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }

        return super.onOptionsItemSelected(item);
    }


}
