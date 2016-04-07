package com.mailab.azharul.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactAdapter extends ArrayAdapter<ContactModel> {

    Context context;
    ArrayList<ContactModel> contactList;

    public ContactAdapter(Context context, ArrayList<ContactModel> objects) {
        super(context, R.layout.custom_layout, objects);
        this.context = context;
        this.contactList = objects;

    }

    static class ViewHolder {
        ImageView pic;
        TextView showNameTV;
        //TextView showPhoneNoTV;

    }

    // generate view by myself
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            // to convert my xml custom layout into view!
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout, null);

            viewHolder = new ViewHolder();
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.showImageID);
            viewHolder.showNameTV = (TextView) convertView.findViewById(R.id.showNameID);
            //viewHolder.showPhoneNoTV= (TextView) convertView.findViewById(R.id.showPhoneNoID);

            convertView.setTag(viewHolder);

            viewHolder.pic.setImageResource(R.drawable.man);
            viewHolder.showNameTV.setText(contactList.get(position).getName());
            //viewHolder.showPhoneNoTV.setText(contactList.get(position).getPhoneNo());


        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }


        return convertView;
    }
}
