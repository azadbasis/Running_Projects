package com.azhar.mycontactwithfragment;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentForContact extends android.app.Fragment {

    EditText nameEt, phoneNoEt;
    Button btnSave;

    private int bitForUpdate = 1;
    private int dataId;

    Contact contact;
    Contact_Manager manager;

    FragmentForDetail fragmentForDetail;

    public FragmentForContact() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_for_contact, container, false);
        // Inflate the layout for this fragment

        nameEt = (EditText) view.findViewById(R.id.nameEt);
        phoneNoEt = (EditText) view.findViewById(R.id.pnoneNoEt);
        btnSave = (Button) view.findViewById(R.id.btnSave);

        manager = new Contact_Manager(getActivity());

        try {
            Bundle getData = getArguments();
            bitForUpdate = getData.getInt("bitForUpdate");
            dataId = getData.getInt("dataId");
            String name = getData.getString("name", "");
            String phoneNo = getData.getString("phoneNo", "");

            if (bitForUpdate == 2) {
                nameEt.setText(name);
                phoneNoEt.setText(phoneNo);

            }
        } catch (Exception e) {
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (bitForUpdate == 1) {

                    String name = nameEt.getText().toString();
                    String phoneNo = phoneNoEt.getText().toString();

                    if (name.length() > 0 && phoneNo.length() > 0) {

                        phoneNoEt.setText("");
                        nameEt.setText("");

                        contact = new Contact(name, phoneNo);
                        boolean inserted = manager.addContact(contact);
                        if (inserted) {
                            Toast.makeText(getActivity(), "Insert Successfull", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getActivity(), "Insert Failed", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "Insert Name and Phone Number", Toast.LENGTH_LONG).show();
                    }

                } else if (bitForUpdate == 2) {

                    String name = nameEt.getText().toString();
                    String phoneNo = phoneNoEt.getText().toString();
                    phoneNoEt.setText("");
                    nameEt.setText("");
                    contact = new Contact(name, phoneNo);
                    manager.updateContact(dataId, contact);
                    Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                    bitForUpdate = 1;

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

            }
        });

        return view;

    }

}