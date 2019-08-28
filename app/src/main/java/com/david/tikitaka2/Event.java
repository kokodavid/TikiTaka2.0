package com.david.tikitaka2;

import org.parceler.Parcel;

@Parcel
public class Event {
    String name;
    String description;
    String start;
    String end;
    String url;
    String pushId;
    String index;

    String Ename;
    String EDate;
    String Etime;
    String EDesc;
    String Eamount;


    public Event() {
    }


    public Event(String name, String description, String start, String end, String url) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.end = end;
        this.index = "not_specified";


        this.Ename = Ename;
        this.EDate = date;
        this.Etime = time;
        this.EDesc = desc;
        this.Eamount = amount;



    }


    public String getEname() {
        return Ename;
    }

    public void setEname(String ename) {
        Ename = ename;
    }

    public String getEDate() {
        return EDate;
    }

    public void setEDate(String EDate) {
        this.EDate = EDate;
    }

    public String getEtime() {
        return Etime;
    }

    public void setEtime(String etime) {
        Etime = etime;
    }

    public String getEDesc() {
        return EDesc;
    }

    public void setEDesc(String EDesc) {
        this.EDesc = EDesc;
    }

    public String getEamount() {
        return Eamount;
    }

    public void setEamount(String eamount) {
        Eamount = eamount;
    }







    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
