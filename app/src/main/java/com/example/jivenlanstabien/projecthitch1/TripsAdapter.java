package com.example.jivenlanstabien.projecthitch1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene on 5/10/2016.
 */
public class TripsAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public TripsAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Trips object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        TripsHolder tripsHolder;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            tripsHolder = new TripsHolder();
            tripsHolder.pickup = (TextView) row.findViewById(R.id.pickup);
            tripsHolder.dropoff = (TextView) row.findViewById(R.id.dropoff);
            tripsHolder.date = (TextView) row.findViewById(R.id.date);
            tripsHolder.time = (TextView) row.findViewById(R.id.time);
            row.setTag(tripsHolder);
        } else {
            tripsHolder = (TripsHolder) row.getTag();
        }

        Trips trips = (Trips) this.getItem(position);
        tripsHolder.pickup.setText(trips.getPickup());
        tripsHolder.dropoff.setText(trips.getDropoff());
        tripsHolder.date.setText(trips.getDate());
        tripsHolder.time.setText(trips.getTime());

        return row;
    }

    static class TripsHolder {
        TextView pickup, dropoff, date, time;
    }
}
