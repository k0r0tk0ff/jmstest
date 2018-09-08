package ru.k0r0tk0ff;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Broker extends Thread {

    private static final Logger LOG  = LoggerFactory.getLogger(Broker.class);

    private boolean isPersistant;
    private boolean isTransacted;
    private String url;
    private String queueName;

    public Broker(boolean isPersistant, boolean isTransacted, String url, String queueName) {
        this.isPersistant = isPersistant;
        this.isTransacted = isTransacted;
        this.url = url;
        this.queueName = queueName;
    }

    public void run() {
        BrokerService brokerService = new BrokerService();
        try {
            brokerService.setPersistent(isPersistant);
            brokerService.setUseJmx(false);
            brokerService.addConnector(url);
            brokerService.start();
            LOG.info("Success start BrokerService ........");

            //brokerService.stop();
       //     brokerService.wait(3000);
       //     LOG.info("Success stop BrokerService ........");
        } catch (Exception e) {
            LOG.error(".......................................................................");
            LOG.error(e.toString());
            LOG.error(".......................................................................");
        }
    }
}
