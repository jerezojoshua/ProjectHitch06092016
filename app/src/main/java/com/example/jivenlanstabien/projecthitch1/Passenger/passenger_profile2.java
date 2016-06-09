package com.example.jivenlanstabien.projecthitch1.Passenger;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jivenlanstabien.projecthitch1.R;

public class passenger_profile2 extends Fragment {

    View PassengerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PassengerView = inflater.inflate(R.layout.activity_passenger_profile2, container, false);
        return PassengerView;
    }
}
