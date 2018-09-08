package ru.k0r0tk0ff;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Receiver extends Thread {

    private static final Logger LOG  = LoggerFactory.getLogger(Receiver.class);

    private String queueName;
    private int mode;
    private boolean isTransacted;
    private String url;

    public Receiver(String queueName, int mode, boolean isTransacted, String url) {
        this.queueName = queueName;
        this.mode = mode;
        this.isTransacted = isTransacted;
        this.url = url;
    }

    public void run() {
        //System.out.println("Receiver -- " + Thread.currentThread().toString());
        receiveMessage(mode, isTransacted, url);
    }

    void receiveMessage(int mode, boolean isTransacted, String url) {

        Connection connection = null;
        Session session = null;
        ConnectionFactory collectionFactory = new ActiveMQConnectionFactory(url);

        try {
            connection = collectionFactory.createConnection();
            connection.start();

            session = connection.createSession(isTransacted, mode);
            Destination destination = session.createQueue(queueName);

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive();

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
            LOG.info("Success received message. Message contains that - '" +
            textMessage.getText() + "'");
            }

            //message.acknowledge();
            session.close();
            connection.close();
        } catch (JMSException e) {
            LOG.error(".......................................................................");
            LOG.error(e.toString());
            LOG.error(".......................................................................");
        }
    }
}
