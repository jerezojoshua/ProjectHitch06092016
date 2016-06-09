package com.example.jivenlanstabien.projecthitch1;

/**
 * Created by Eugene on 5/10/2016.
 */
public class Schedule {
    String scheduleid, driverid, vehicleid, pickup, dropoff, date, time, preference, pattern, monday,
            tuesday, wednesday, thursday, friday, saturday, sunday, noweeks, enddate;
    Integer luggage;

    public Schedule(String scheduleid,String driverid,String vehicleid,String pickup,String dropoff,String date,
                    String time,String preference,Integer luggage,String pattern,String monday,String tuesday,String wednesday,String thursday,
                    String friday,String saturday,String sunday,String noweeks,String enddate){

        this.setScheduleid(scheduleid);
        this.setDriverid(driverid);
        this.setVehicleid(vehicleid);
        this.setPickup(pickup);
        this.setDropoff(dropoff);
        this.setDate(date);
        this.setTime(time);
        this.setPreference(preference);
        this.setLuggage(luggage);
        this.setPattern(pattern);
        this.setMonday(monday);
        this.setTuesday(tuesday);
        this.setWednesday(wednesday);
        this.setThursday(thursday);
        this.setFriday(friday);
        this.setSaturday(saturday);
        this.setSunday(sunday);
        this.setNoweeks(noweeks);
        this.setEnddate(enddate);
    }

    public String getScheduleid() {
        return scheduleid;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public Integer getLuggage() {
        return luggage;
    }

    public void setLuggage(Integer luggage) {
        this.luggage = luggage;
    }

    public void setScheduleid(String scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDropoff() {
        return dropoff;
    }

    public void setDropoff(String dropoff) {
        this.dropoff = dropoff;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getNoweeks() {
        return noweeks;
    }

    public void setNoweeks(String noweeks) {
        this.noweeks = noweeks;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
