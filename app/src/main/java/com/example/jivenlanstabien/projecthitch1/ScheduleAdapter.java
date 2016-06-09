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
public class ScheduleAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ScheduleAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Schedule object) {
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
        ScheduleHolder scheduleHolder;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            scheduleHolder = new ScheduleHolder();
            scheduleHolder.pickup = (TextView) row.findViewById(R.id.pickup);
            scheduleHolder.dropoff = (TextView) row.findViewById(R.id.dropoff);
            scheduleHolder.date = (TextView) row.findViewById(R.id.date);
            scheduleHolder.time = (TextView) row.findViewById(R.id.time);
            row.setTag(scheduleHolder);
        } else {
            scheduleHolder = (ScheduleHolder) row.getTag();
        }

        Schedule schedule = (Schedule) this.getItem(position);
        scheduleHolder.pickup.setText(schedule.getPickup());
        scheduleHolder.dropoff.setText(schedule.getDropoff());
        scheduleHolder.date.setText(schedule.getDate());
        scheduleHolder.time.setText(schedule.getTime());

        return row;
    }

    static class ScheduleHolder {
        TextView pickup, dropoff, date, time;
    }
}
