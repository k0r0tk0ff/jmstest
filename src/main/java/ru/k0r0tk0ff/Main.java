package ru.k0r0tk0ff;

import org.apache.activemq.broker.BrokerService;

public class Main {

    public static void main(String[]args){
        Sender sender = new Sender();
        Receiver receiver = new Receiver();

        BrokerService brokerService = new BrokerService();
        try {
            brokerService.setPersistent(false);
            brokerService.setUseJmx(false);
            brokerService.addConnector("tcp://localhost:61616");

            brokerService.start();
            sender.sendMessage();
            receiver.receiveMessage();

            brokerService.stop();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}