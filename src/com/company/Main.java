package com.company;

public class Main {

    public static void main(String[] args){
        View_Data view = new View_Data();
        Shop shop = new Shop(view);
        Controller controller = new Controller(view,shop);
    }
}
