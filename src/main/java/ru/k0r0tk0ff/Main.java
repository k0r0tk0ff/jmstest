package ru.k0r0tk0ff;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Session;

public class Main {

    private static final Logger LOG  = LoggerFactory.getLogger(Main.class);
    private static final boolean ISPERSISTANT = true;
    private static final boolean ISTRANSACTED = true;
    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUENAME = "queue";

    //private static final int MODE = Session.SESSION_TRANSACTED;
    //private static final int MODE = Session.AUTO_ACKNOWLEDGE;
    //private static final int MODE = Session.DUPS_OK_ACKNOWLEDGE;
    private static final int MODE = Session.CLIENT_ACKNOWLEDGE;

    public static void main(String[]args){

        LOG.info("Start application jmstest ..............");
        LOG.info("MODE = CLIENT_ACKNOWLEDGE");

        Sender sender = new Sender(QUEUENAME);
        LOG.info("Success create sender object........");
        
        Receiver receiver = new Receiver(QUEUENAME);
        LOG.info("Success create receiver object ........");

        BrokerService brokerService = new BrokerService();
        try {
            brokerService.setPersistent(ISPERSISTANT);
            brokerService.setUseJmx(false);
            brokerService.addConnector(URL);
            System.out.println(brokerService.isPersistent());
            brokerService.start();
            LOG.info("Success start BrokerService ........");
            sender.sendMessage(MODE, ISTRANSACTED, URL);
            receiver.receiveMessage(MODE, ISTRANSACTED, URL);
            brokerService.stop();
            LOG.info("Success stop BrokerService ........");
        } catch (Exception e) {
            LOG.error(".......................................................................");
            LOG.error(e.toString());
            LOG.error(".......................................................................");
        }
    }
}
