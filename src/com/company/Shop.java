package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Shop {

    private AtomicInteger time = new AtomicInteger();
    private View_Data view;
    private int nrQueues;
    private int nrClients;
    private int simulationTime;
    private int minimumArrival;
    private int maximumArrival;
    private int maximumService;
    private int minimumService;
    private ArrayList<Coada> queues;
    private ArrayList<Client> clients;
    private File myFile = new File("log.txt");
    private FileWriter myWriter;

    public Shop(View_Data view)
    {
        this.view = view;
    }

    public void generateClients()
    {
        clients = new ArrayList<>(nrClients);
        Random random = new Random();
        for(int i=0; i<nrClients; i++)
        {
            Client client = new Client(i,random.nextInt(view.getMaximumArrivalTime()-view.getMinimumArrivalTime())+view.getMinimumArrivalTime(),random.nextInt(view.getMaximumServiceTime()-view.getMinimumServiceTime())+view.getMinimumServiceTime());
            clients.add(client);
        }
        Collections.sort(clients);
        for(int i=0; i<nrClients; i++)
        {
            clients.get(i).setId(i);
        }
    }

    public void generateQueues() {
        queues = new ArrayList<>(nrQueues);
        for (int i = 0; i < nrQueues; i++)
            queues.add(new Coada(i,nrClients,time));
    }

    public void showClients()
    {
        if(!myFile.exists())
        {
            try{
                myFile.createNewFile();
            }
            catch (IOException e)
            {
                System.out.println("Can't open the file");
                e.printStackTrace();
            }
        }
        if(myWriter == null)
        {
            try {
                myWriter = new FileWriter("log.txt");
            }
            catch (IOException e)
            {
                System.out.println("Can't open the writer");
                e.printStackTrace();
            }
        }
        try {
            myWriter.write("Input\nNr. queues:"+getNrQueues()+"\nNr. clients:"+getNrClients()+"\nSimulation Time:"+getSimulationTime()+"\nTmin arrival:"+minimumArrival
                    +"\nTmax arrival:"+maximumArrival+"\nTmin service:"+minimumService+"\nTmax service"+maximumService+"\n");
            myWriter.write("Random generated clients\n");
            for (int i = 0; i < nrClients; i++) {
                myWriter.write(clients.get(i).toString());
            }
            myWriter.flush();
        }
        catch (IOException e)
        {
            System.out.println("Error when writing the clients");
            e.printStackTrace();
        }
    }

    public AtomicInteger getTime() {
        return time;
    }

    public void setTime(AtomicInteger time) {
        this.time = time;
    }

    public int getNrQueues() {
        return nrQueues;
    }

    public void setNrQueues(int nrQueues) {
        this.nrQueues = nrQueues;
    }

    public int getNrClients() {
        return nrClients;
    }

    public void setNrClients(int nrClients) {
        this.nrClients = nrClients;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    public ArrayList<Coada> getQueues() {
        return queues;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setMinimumArrival(int minimumArrival) {
        this.minimumArrival = minimumArrival;
    }

    public void setMaximumArrival(int maximumArrival) {
        this.maximumArrival = maximumArrival;
    }


    public void setMaximumService(int maximumService) {
        this.maximumService = maximumService;
    }

    public void setMinimumService(int minimumService) {
        this.minimumService = minimumService;
    }

    public void printDetails() {

        if(!myFile.exists())
        {
            try{
                myFile.createNewFile();
            }
            catch (IOException e)
            {
                System.out.println("Can't open the file");
                e.printStackTrace();
            }
        }
        if(myWriter == null)
        {
            try {
                myWriter = new FileWriter("log.txt");
            }
            catch (IOException e)
            {
                System.out.println("Can't open the writer");
                e.printStackTrace();
            }
        }
        try {
            myWriter.write("\nCurrent time: " + time+"\nWaiting list: ");
            for(Client i: clients)
            {
                myWriter.write(i.toString());
            }
            myWriter.write("\n");
            for (Coada i : queues)
                myWriter.write(i.toString()+"\n");
            myWriter.flush();
        }
        catch (IOException e)
        {
            System.out.println("Can't write");
            e.printStackTrace();
        }
    }

    public void printFinalDetails()
    {
        if(!myFile.exists())
        {
            try{
                myFile.createNewFile();
            }
            catch (IOException e)
            {
                System.out.println("Can't open the file");
                e.printStackTrace();
            }
        }
        if(myWriter == null)
        {
            try {
                myWriter = new FileWriter("log.txt");
            }
            catch (IOException e)
            {
                System.out.println("Can't open the writer");
                e.printStackTrace();
            }
        }
        try {
            myWriter.write("\nStatistics\n");
            for(Coada i: queues)
            {
                myWriter.write("Coada "+i.getIdQ()+": avgTime="+i.getAverageWaitingTime()/i.getNrOfClients()+" peak at "+i.getPeak()+
                        " with "+i.getMaxClients()+" clients\n");
                myWriter.flush();
            }
        }
        catch (IOException e)
        {
            System.out.println("Can't write");
            e.printStackTrace();
        }
    }

}
