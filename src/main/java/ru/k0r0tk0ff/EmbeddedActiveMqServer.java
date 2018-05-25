package ru.k0r0tk0ff;


import org.apache.activemq.broker.BrokerService;

public class EmbeddedActiveMqServer {

    public void startActiveMqServer(BrokerService brokerService) {

    //BrokerService brokerService = new BrokerService();
        try {
            brokerService.setPersistent(false);
            brokerService.setUseJmx(false);
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopActiveMqServer(BrokerService brokerService) {
        try {
            brokerService.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
