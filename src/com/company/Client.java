package com.company;

public class Client implements Comparable{

    private int id;
    private int tArrival;
    private int tService;

    public Client(int id, int tArrival, int tService)
    {
        this.id=id;
        this.tArrival=tArrival;
        this.tService=tService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int gettArrival() {
        return tArrival;
    }

    public void settArrival(int tArrival) {
        this.tArrival = tArrival;
    }

    public int gettService() {
        return tService;
    }

    public void settService(int tService) {
        this.tService = tService;
    }

    @Override
    public int compareTo(Object o) {
        Client c = (Client)o;
        if(this.tArrival < c.gettArrival())
            return -1;
        return 1;
    }
    public String toString()
    {
        return " ("+id+","+tArrival+","+tService+") ";
    }
}
