package ru.k0r0tk0ff;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {

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
            System.out.println("Received message is '" + textMessage.getText() + "'");
        }

        connection.close();

    } catch (JMSException ex) {
        ex.printStackTrace();
    }

    }

}
