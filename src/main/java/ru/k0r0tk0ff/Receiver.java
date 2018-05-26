package ru.k0r0tk0ff;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Receiver {

    private static final Logger LOG  = LoggerFactory.getLogger(Receiver.class);

    public void receiveMessage() {

        Connection connection = null;
        Session session = null;

        //Name of queue
        String queueName = "queue";

        ConnectionFactory collectionFactory = new ActiveMQConnectionFactory();

        try {
            connection = collectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive();

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;

                if(LOG.isDebugEnabled()) {
                    LOG.debug("........................Success received message. Message contains that - '" +
                            textMessage.getText() + "'");
                }
            }

            connection.close();

        } catch (JMSException e) {
            LOG.error(".......................................................................");
            LOG.error(e.toString());
            LOG.error(".......................................................................");
        }

    }

}
