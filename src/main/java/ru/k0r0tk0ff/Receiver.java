package ru.k0r0tk0ff;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Receiver {

    private static final Logger LOG  = LoggerFactory.getLogger(Receiver.class);

    private String queueName;

    public Receiver(String queueName) {
        this.queueName = queueName;
    }

    public void receiveMessage(int mode, boolean isTransacted, String url) {

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

            message.acknowledge();
            session.close();
            connection.close();

        } catch (JMSException e) {
            LOG.error(".......................................................................");
            LOG.error(e.toString());
            LOG.error(".......................................................................");
        }

    }

}
