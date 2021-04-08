package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View_Data {
    JFrame frame = new JFrame("Data");
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JLabel nrOfQueues = new JLabel("Nr. queues");
    JTextField nrOfQueuesText = new JTextField(10);

    JLabel nrOfClients = new JLabel("Nr. clients");
    JTextField nrOfClientsText = new JTextField(10);

    JLabel simulationTime = new JLabel("Simulation Time");
    JTextField simulationTimeText = new JTextField(10);

    JLabel clientArrivalTime = new JLabel("Arrival times");
    JTextField clientArrivalTimeLowText = new JTextField(10);
    JTextField clientArrivalTimeHighText = new JTextField(10);

    JLabel serviceTime = new JLabel("Service Time");
    JTextField serviceTimeLowText = new JTextField(10);
    JTextField serviceTimeHighText = new JTextField(10);

    JButton button = new JButton("Submit");
    public View_Data()
    {
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400,300));
        frame.setResizable(false);

        panel.setLayout(new GridLayout(6,1));

        panel1.add(nrOfQueues);
        panel1.add(nrOfQueuesText);

        panel2.add(nrOfClients);
        panel2.add(nrOfClientsText);

        panel3.add(simulationTime);
        panel3.add(simulationTimeText);

        panel4.add(clientArrivalTime);
        panel4.add(clientArrivalTimeLowText);
        panel4.add(clientArrivalTimeHighText);

        panel5.add(serviceTime);
        panel5.add(serviceTimeLowText);
        panel5.add(serviceTimeHighText);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(button);

        frame.add(panel);
        frame.setVisible(true);
    }

    public int getNrOfQueues()
    {
        return Integer.parseInt(nrOfQueuesText.getText());
    }

    public int getNrOfClients()
    {
        return Integer.parseInt(nrOfClientsText.getText());
    }

    public int getSimulationTime()
    {
        return Integer.parseInt(simulationTimeText.getText());
    }
    public int getMinimumArrivalTime()
    {
        return Integer.parseInt(clientArrivalTimeLowText.getText());
    }

    public int getMaximumArrivalTime()
    {
        return Integer.parseInt(clientArrivalTimeHighText.getText());
    }

    public int getMinimumServiceTime()
    {
        return Integer.parseInt(serviceTimeLowText.getText());
    }

    public int getMaximumServiceTime()
    {
        return Integer.parseInt(serviceTimeHighText.getText());
    }

    public void addActionListener(ActionListener a)
    {
        button.addActionListener(a);
    }
}
