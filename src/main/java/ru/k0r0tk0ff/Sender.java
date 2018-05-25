package ru.k0r0tk0ff;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Sender {
    public void sendMessage() {

        ConnectionFactory f = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "failover://tcp://localhost:61616");

        try {
            Connection connection = f.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("queue");

            MessageProducer producer = session.createProducer(destination);

            TextMessage message = session.createTextMessage("Message for send");

            producer.send(message);

            System.out.println("Messaage '" + message.getText() + "' has been sent");
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
