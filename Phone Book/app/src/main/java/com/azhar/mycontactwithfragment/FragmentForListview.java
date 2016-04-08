package com.azhar.mycontactwithfragment;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentForListview extends android.app.Fragment {


    ListView contactListLv;

    Contact_Manager manager;
    Contact contact;
    ArrayList<Contact> contactList;
    AdapterForContact adapter;

    FragmentForDetail fragmentForDetail;

    public FragmentForListview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_for_listview, container, false);
        // Inflate the layout for this fragment
        contactListLv = (ListView) view.findViewById(R.id.contactListLv);

        contact = new Contact();
        manager = new Contact_Manager(getActivity());

        contactList = new ArrayList<>();
        contactList = manager.getAllContacts();
        adapter = new AdapterForContact(getActivity(), contactList);

        contactListLv.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        contactListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String clickedItem = parent.getItemAtPosition(position).toString();

                int dataId = contactList.get(position).getId();
                Bundle data = new Bundle();
                data.putInt("dataId", dataId);

                fragmentForDetail = new FragmentForDetail();

                fragmentForDetail.setArguments(data);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.layout, fragmentForDetail);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();


            }
        });


        return view;
    }

}
