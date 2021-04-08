package com.company;

public class Manager extends Thread{
    private Shop shop;

    public Manager(Shop shop)
    {
        this.shop=shop;
    }

    @Override
    public synchronized void run() {
        for(Coada i: shop.getQueues()) {
            i.setWorking(true);
            i.start();
        }
        int currentTime = shop.getTime().get();
            while (shop.getSimulationTime() > currentTime) {

                ///adaug clientul in coada cu timpul de asteptare cel mai mic
                int queueNumber = searchBestQueue();
                if (shop.getClients().size() > 0) {
                    for (int i = 0; i < shop.getClients().size(); i++)
                        if (shop.getClients().get(i).gettArrival() == currentTime && shop.getClients().get(i).gettService()+currentTime <= shop.getSimulationTime()) {
                            putInQueue(shop.getClients().get(i), queueNumber);
                            shop.getQueues().get(queueNumber).setNrOfClients(shop.getQueues().get(queueNumber).getNrOfClients()+1);
                            shop.getClients().remove(i);
                            i--;
                            queueNumber = searchBestQueue();
                        }
                }
                shop.printDetails();

                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                shop.getTime().incrementAndGet();
                currentTime++;

                if(shop.getTime().get() == shop.getSimulationTime())
                {
                    for(Coada i: shop.getQueues())
                        i.setWorking(false);
                    shop.printFinalDetails();
                }
            }
    }

    public int searchBestQueue()
    {
        int min = shop.getQueues().get(0).getWaitingTime();
        int pozMin=0;
        for(Coada i : shop.getQueues())
            if( min > i.getWaitingTime()) {
                min = i.getWaitingTime();
                pozMin= i.getIdQ();
            }
        return pozMin;
    }

    public synchronized void putInQueue(Client client, int index)
    {
        shop.getQueues().get(index).getQueue().add(client);
        shop.getQueues().get(index).setWaitingTime(shop.getQueues().get(index).getWaitingTime()+client.gettService());
    }
}
