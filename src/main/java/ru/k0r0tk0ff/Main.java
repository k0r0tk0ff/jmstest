package ru.k0r0tk0ff;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Session;

public class Main {

    private static final Logger LOG  = LoggerFactory.getLogger(Main.class);

    private static final boolean ISPERSISTANT = false;
    private static final boolean ISTRANSACTED = false;
    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUENAME = "queue";

    //private static final int MODE = Session.SESSION_TRANSACTED;
    private static final int MODE = Session.AUTO_ACKNOWLEDGE;
    //private static final int MODE = Session.DUPS_OK_ACKNOWLEDGE;
    //private static final int MODE = Session.CLIENT_ACKNOWLEDGE;


    public static void main(String[]args){

        LOG.info("Start application jmstest ..............");
        LOG.info("MODE = Session.AUTO_ACKNOWLEDGE");

        //System.out.println(Thread.currentThread().toString());
        Sender sender = new Sender(QUEUENAME, MODE, ISTRANSACTED, URL);
        LOG.info("Success create sender object........");
        Receiver receiver = new Receiver(QUEUENAME, MODE, ISTRANSACTED, URL);
        LOG.info("Success create receiver object ........");

        try {
            Broker broker = new Broker(ISPERSISTANT, ISTRANSACTED, URL, QUEUENAME);
            broker.start();

            sender.start();
            sender.join();

            receiver.start();
            receiver.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
    }
}
