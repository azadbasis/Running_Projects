package com.azhar.mycontactwithfragment;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentForDetail extends android.app.Fragment {

    TextView nameTv, phoneNoTv;

    Button btnPhoneCall,btnMessage,btnUpdate,btnDelete;

    int dataId=1;
    Contact_Manager manager;

    Contact contact;

    FragmentForContact fragmentForContact;
    FragmentForListview fragmentForListview;

    public FragmentForDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_for_detail, container, false);
        // Inflate the layout for this fragment

        nameTv =(TextView)view.findViewById(R.id.nameTv);
        phoneNoTv =(TextView)view.findViewById(R.id.phoneNoTv);

        btnPhoneCall =(Button)view.findViewById(R.id.btnPhoneCall);
        btnMessage =(Button)view.findViewById(R.id.btnMessage);
        btnUpdate =(Button)view.findViewById(R.id.btnUpdate);
        btnDelete =(Button)view.findViewById(R.id.btnDelete);

        manager = new Contact_Manager(getActivity());
        contact =new Contact();

        Bundle getData = getArguments();
        dataId = getData.getInt("dataId");


        nameTv.setText(manager.getContact(dataId).getName());
        phoneNoTv.setText(manager.getContact(dataId).getPhoneNo());


        btnPhoneCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        btnMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               // manager.updateContact(dataId, contact);
                Bundle data = new Bundle();
                data.putInt("bitForUpdate", 2);
                data.putInt("dataId",dataId);
                data.putString("name", nameTv.getText().toString());
                data.putString("phoneNo", phoneNoTv.getText().toString());


                fragmentForContact = new FragmentForContact();
                fragmentForContact.setArguments(data);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.layout, fragmentForContact);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();


            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                manager.deleteContact(dataId);

                 fragmentForListview= new FragmentForListview();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.layout, fragmentForListview);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();





            }
        });




        return view;
    }

}
