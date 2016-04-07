package com.azhar.android.shareprefarence;

/**
 * Created by azadidb on 3/21/2016.
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomizedAdapter extends ArrayAdapter<Student> {
    Activity con;
    ArrayList<Student> employeeList;

    public CustomizedAdapter(Context context, ArrayList<Student> employees) {
        super(context, R.layout.list_item, employees);
        this.con = (Activity)context;
        this.employeeList = employees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if (convertView == null) {
            // generate a view and return
            LayoutInflater inflater=con.getLayoutInflater();
            v = inflater.inflate(R.layout.list_item, null);

            TextView txtName=(TextView)v.findViewById(R.id.txtName);
            TextView txtEmail=(TextView)v.findViewById(R.id.txtEmail);
            TextView txtPhone=(TextView)v.findViewById(R.id.txtPhone);
            TextView txtDesignation=(TextView)v.findViewById(R.id.txtDesignation);
            Student e=employeeList.get(position);
            txtName.setText(e.getName());
            txtEmail.setText(e.getEmail());
            txtPhone.setText(e.getPhone());
            txtDesignation.setText(e.getDesignation());

        } else {
            v = convertView;
        }
        return v;
    }

}
