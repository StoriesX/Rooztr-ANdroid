package com.rooztr.rooztr_android;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rooztr.model.Contact;

/**
 * Created by anandsuresh on 7/20/16.
 */
public class ContactDetails extends Fragment{
    private TextView textName;
    private TextView textNumbers;
    String info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.details,container,false);

        textName = (TextView) view.findViewById(R.id.detailText);
        textNumbers = (TextView) view.findViewById(R.id.detailNumbers);

        info = getArguments().getString("id");
        Contact contact = MainActivity.contactMap.get(info);

        textName.setText(contact.getName());
        textName.setTextColor(Color.RED);

        for(String val : contact.getNumbers()) {
            textNumbers.append(val);
            textNumbers.append("\n");

        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(), "hehe" + info, Toast.LENGTH_SHORT).show();
    }

}
