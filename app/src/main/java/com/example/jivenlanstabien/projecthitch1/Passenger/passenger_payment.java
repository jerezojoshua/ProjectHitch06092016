package com.example.jivenlanstabien.projecthitch1.Passenger;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jivenlanstabien.projecthitch1.R;

/**
 * Created by Carlo Ortega on 4/19/2016.
 */
public class passenger_payment extends Fragment {

    View PassengerPayment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PassengerPayment = inflater.inflate(R.layout.activity_passenger_payment, container, false);
        return PassengerPayment;
    }
}
