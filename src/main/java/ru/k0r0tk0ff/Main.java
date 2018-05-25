package ru.k0r0tk0ff;

import org.apache.activemq.broker.BrokerService;

public class Main {

    public static void main(String[]args){
       // Sender sender = new Sender();
       // Receiver receiver = new Receiver();
        EmbeddedActiveMqServer activeMqServer = new EmbeddedActiveMqServer();
        BrokerService brokerService = new BrokerService();

        activeMqServer.startActiveMqServer(brokerService);

      //  sender.sendMessage();

      //  receiver.receiveMessage();

        activeMqServer.startActiveMqServer(brokerService);

    }

}