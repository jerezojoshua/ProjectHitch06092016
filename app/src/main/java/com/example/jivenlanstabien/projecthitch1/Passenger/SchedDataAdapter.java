package com.example.jivenlanstabien.projecthitch1.Passenger;

/**
 * Created by JivenlansTabien on 5/11/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jivenlanstabien.projecthitch1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 5/11/2016.
 */
public class SchedDataAdapter extends ArrayAdapter {

    List list = new ArrayList<>();

    public SchedDataAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(SchedData object) {
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
        SchedDataHolder schedDataHolder;

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            schedDataHolder = new SchedDataHolder();
            schedDataHolder.tx_pickup  = (TextView) row.findViewById(R.id.pickup);
            schedDataHolder.tx_dropoff = (TextView) row.findViewById(R.id.dropoff);
            schedDataHolder.tx_date = (TextView) row.findViewById(R.id.date);
            schedDataHolder.tx_time = (TextView) row.findViewById(R.id.time);
            row.setTag(schedDataHolder);
        }
        else
        {
            schedDataHolder = (SchedDataHolder)row.getTag();
        }

        SchedData schedData= (SchedData) this.getItem(position);
        schedDataHolder.tx_pickup.setText(schedData.getPickup());
        schedDataHolder.tx_dropoff.setText(schedData.getDropoff());
        schedDataHolder.tx_date.setText(schedData.getDate());
        schedDataHolder.tx_time.setText(schedData.getTime());
        return row;
    }

    static class SchedDataHolder
    {
        TextView tx_pickup,tx_dropoff,tx_date,tx_time;
    }
}
