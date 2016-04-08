package com.azhar.mycontactwithfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Azhar on 4/7/2016.
 */
public class AdapterForContact extends ArrayAdapter {

    private ArrayList<Contact> contactsArrayList;
    private Context context;


    public AdapterForContact(Context context, ArrayList<Contact> contactsArrayList) {
        super(context, R.layout.custom_layout_for_adapter, contactsArrayList);
        this.context = context;
        this.contactsArrayList = contactsArrayList;
    }

    static class ViewHolder {

        TextView nameTv;
        TextView phoneTv;
        ImageView photoIv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_layout_for_adapter, null);


            viewHolder = new ViewHolder();
            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.nameTv);
            viewHolder.phoneTv = (TextView) convertView.findViewById(R.id.phoneNoTv);
            viewHolder.photoIv = (ImageView) convertView.findViewById(R.id.photoIv);

            convertView.setTag(viewHolder);

        } else {


            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.nameTv.setText(contactsArrayList.get(position).getName());
        viewHolder.phoneTv.setText(contactsArrayList.get(position).getPhoneNo());
        viewHolder.photoIv.setImageResource(contactsArrayList.get(position).getImageId());


        return convertView;

    }


}
