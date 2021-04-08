package com.company;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private View_Data view;
    private Shop shop;
    private Manager manager;

    public Controller(View_Data view, Shop shop) {
        this.view = view;
        this.shop = shop;
        this.view.addActionListener(new ButtonListener());
        manager = new Manager(shop);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                int check = view.getNrOfQueues();
            } catch (NumberFormatException e) {
                System.out.println("The number of queues is invalid");
            }
            try {
                int check = view.getNrOfClients();
            } catch (NumberFormatException e) {
                System.out.println("The number of clients is invalid");
            }
            try {
                int check = view.getSimulationTime();
            } catch (NumberFormatException e) {
                System.out.println("The number for simulation time is invalid");
            }
            try {
                int check = view.getMinimumArrivalTime();
                int check2 = view.getMaximumArrivalTime();
            } catch (NumberFormatException e) {
                System.out.println("The numbers for arrival time are invalids");
            }
            try {
                int check = view.getMinimumServiceTime();
                int check2 = view.getMaximumServiceTime();
            } catch (NumberFormatException e) {
                System.out.println("The numbers for service time are invalids");
            }
            try {
                int check = view.getMaximumArrivalTime();
                int check2 = view.getMaximumServiceTime();
                if (check > view.getSimulationTime() || check2 > view.getSimulationTime())
                    throw new Exception();
            } catch (Exception e) {
                System.out.println("Maximum arrival time or maximum service time is invalid");
            }
            shop.setNrQueues(view.getNrOfQueues());
            shop.setNrClients(view.getNrOfClients());
            shop.setSimulationTime(view.getSimulationTime());
            shop.setMinimumArrival(view.getMinimumArrivalTime());
            shop.setMaximumArrival(view.getMaximumArrivalTime());
            shop.setMinimumService(view.getMinimumServiceTime());
            shop.setMaximumService(view.getMaximumServiceTime());

            view.frame.dispose();
            shop.generateClients();
            shop.generateQueues();
            shop.showClients();
            manager.run();
        }
    }
}
