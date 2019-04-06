package com.example.work;

public class Data {
    String name=null;
    String number=null;
    String name1=null;
    String number1=null;

    long date;
    long duration;

    public Data(String name, String number, long date, long duration) {
        this.name = name;
        this.number = number;
        this.date = date;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public long getDate() {
        return date;
    }

    public long getDuration() {
        return duration;
    }

    public String getName1() {
        return name1;
    }

    public String getNumber1() {
        return number1;
    }

    public Data(String name1, String number1) {
        this.name1 = name1;
        this.number1 = number1;
    }
}
