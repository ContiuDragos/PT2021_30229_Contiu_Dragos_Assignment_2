package com.company;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Coada extends Thread{
    private boolean working = false;
    private AtomicInteger time;
    private int waitingTime = 0;
    private int id;
    private float averageWaitingTime=0;
    private int nrOfClients=0;
    private int peak;
    private int maxClients;
    private ArrayList<Client> queue;
    public Coada(int id, int nrClients, AtomicInteger time)
    {
        this.id=id;
        queue = new ArrayList<>(nrClients);
        this.time=time;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public ArrayList<Client> getQueue() {
        return queue;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public int getIdQ() {
        return id;
    }

    public synchronized void addClient(Client client)
    {
        queue.add(client);
    }

    public float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void setAverageWaitingTime(float averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }

    public int getNrOfClients() {
        return nrOfClients;
    }

    public void setNrOfClients(int nrOfClients) {
        this.nrOfClients = nrOfClients;
    }

    public int getPeak() {
        return peak;
    }

    public void setPeak(int peak) {
        this.peak = peak;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public void setMaxClients(int maxClients) {
        this.maxClients = maxClients;
    }

    public String toString() {
        String result="Coada "+id+": ";
        for(Client i:queue)
            result= result + i.toString();
        if(queue.size()==0)
            result= result + "closed";
        return result;
    }

    @Override
    public void run() {

        while(working) {
            if (queue.size() > 0) {
                waitingTime--;
                queue.get(0).settService(queue.get(0).gettService() - 1);
                if (queue.get(0).gettService() == 0) {
                    queue.remove(0);
                    if(queue.size() >= 1)
                        averageWaitingTime += time.get()-queue.get(0).gettArrival();
                }

                if(queue.size() > maxClients)
                {
                    maxClients=queue.size();
                    peak=time.get();
                }

            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
