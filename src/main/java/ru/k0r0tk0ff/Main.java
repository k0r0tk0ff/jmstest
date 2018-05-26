package ru.k0r0tk0ff;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG  = LoggerFactory.getLogger(Main.class);

    public static void main(String[]args){

        if(LOG.isDebugEnabled()) {
            LOG.debug("Start application jmstest ..............");
        }

        Sender sender = new Sender();

        if(LOG.isDebugEnabled()) {
            LOG.debug("Success create sender object........");
        }
        
        Receiver receiver = new Receiver();

        if(LOG.isDebugEnabled()) {
            LOG.debug("Success create receiver object ........");
        }

        BrokerService brokerService = new BrokerService();
        try {
            brokerService.setPersistent(false);
            brokerService.setUseJmx(false);
            brokerService.addConnector("tcp://localhost:61616");

            brokerService.start();

            if(LOG.isDebugEnabled()) {
                LOG.debug("Success start BrokerService ........");
            }
            
            sender.sendMessage();

/*
            if(LOG.isDebugEnabled()) {
                LOG.debug("Success send message ........");
            }
*/

            receiver.receiveMessage();
/*            if(LOG.isDebugEnabled()) {
                LOG.debug("Success receive message ........");
            }*/

            brokerService.stop();

            if(LOG.isDebugEnabled()) {
                LOG.debug("Success stop BrokerService ........");
            }
        } catch (Exception e) {
            LOG.error(".......................................................................");
            LOG.error(e.toString());
            LOG.error(".......................................................................");
        }
    }
}