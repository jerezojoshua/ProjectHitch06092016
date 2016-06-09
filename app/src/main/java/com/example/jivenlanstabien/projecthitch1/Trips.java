package com.example.jivenlanstabien.projecthitch1;

/**
 * Created by Eugene on 5/10/2016.
 */
public class Trips {
    String scheduleid, driverid, vehicleid, pickup, dropoff, date, time, seat1, seat1_passenger, seat2, seat2_passenger, seat3,
            seat3_passenger, seat4, seat4_passenger, seat5, seat5_passenger, seat6, seat6_passenger, seat7, seat7_passenger;

    public Trips(String scheduleid, String driverid, String vehicleid, String pickup, String dropoff, String date,
                 String time, String seat1, String seat1_passenger, String seat2, String seat2_passenger, String seat3, String seat3_passenger
            , String seat4, String seat4_passenger, String seat5, String seat5_passenger, String seat6, String seat6_passenger
            , String seat7, String seat7_passenger) {

        this.setScheduleid(scheduleid);
        this.setDriverid(driverid);
        this.setVehicleid(vehicleid);
        this.setPickup(pickup);
        this.setDropoff(dropoff);
        this.setDate(date);
        this.setTime(time);
        this.setSeat1(seat1);
        this.setSeat1_passenger(seat1_passenger);
        this.setSeat2(seat2);
        this.setSeat2_passenger(seat2_passenger);
        this.setSeat3(seat3);
        this.setSeat3_passenger(seat3_passenger);
        this.setSeat4(seat4);
        this.setSeat4_passenger(seat4_passenger);
        this.setSeat5(seat5);
        this.setSeat5_passenger(seat5_passenger);
        this.setSeat6(seat6);
        this.setSeat6_passenger(seat6_passenger);
        this.setSeat7(seat7);
        this.setSeat7_passenger(seat7_passenger);
    }

    public String getScheduleid() {
        return scheduleid;
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

    public String getSeat1() {
        return seat1;
    }

    public void setSeat1(String seat1) {
        this.seat1 = seat1;
    }

    public String getSeat1_passenger() {
        return seat1_passenger;
    }

    public void setSeat1_passenger(String seat1_passenger) {
        this.seat1_passenger = seat1_passenger;
    }

    public String getSeat2() {
        return seat2;
    }

    public void setSeat2(String seat2) {
        this.seat2 = seat2;
    }

    public String getSeat2_passenger() {
        return seat2_passenger;
    }

    public void setSeat2_passenger(String seat2_passenger) {
        this.seat2_passenger = seat2_passenger;
    }

    public String getSeat3() {
        return seat3;
    }

    public void setSeat3(String seat3) {
        this.seat3 = seat3;
    }

    public String getSeat3_passenger() {
        return seat3_passenger;
    }

    public void setSeat3_passenger(String seat3_passenger) {
        this.seat3_passenger = seat3_passenger;
    }

    public String getSeat4() {
        return seat4;
    }

    public void setSeat4(String seat4) {
        this.seat4 = seat4;
    }

    public String getSeat4_passenger() {
        return seat4_passenger;
    }

    public void setSeat4_passenger(String seat4_passenger) {
        this.seat4_passenger = seat4_passenger;
    }

    public String getSeat5() {
        return seat5;
    }

    public void setSeat5(String seat5) {
        this.seat5 = seat5;
    }

    public String getSeat5_passenger() {
        return seat5_passenger;
    }

    public void setSeat5_passenger(String seat5_passenger) {
        this.seat5_passenger = seat5_passenger;
    }

    public String getSeat6() {
        return seat6;
    }

    public void setSeat6(String seat6) {
        this.seat6 = seat6;
    }

    public String getSeat6_passenger() {
        return seat6_passenger;
    }

    public void setSeat6_passenger(String seat6_passenger) {
        this.seat6_passenger = seat6_passenger;
    }

    public String getSeat7() {
        return seat7;
    }

    public void setSeat7(String seat7) {
        this.seat7 = seat7;
    }

    public String getSeat7_passenger() {
        return seat7_passenger;
    }

    public void setSeat7_passenger(String seat7_passenger) {
        this.seat7_passenger = seat7_passenger;
    }
}
