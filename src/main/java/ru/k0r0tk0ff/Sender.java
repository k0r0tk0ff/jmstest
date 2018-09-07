package ru.k0r0tk0ff;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;


public class Sender {

    private static final Logger LOG  = LoggerFactory.getLogger(Sender.class);

    private String queueName;

    public Sender(String queueName) {
        this.queueName = queueName;
    }

    public void sendMessage(int mode, boolean isTransacted, String url) {

        ConnectionFactory f = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
          //      "failover://tcp://localhost:61616");
                url);

        try {
            Connection connection = f.createConnection();
            Session session = connection.createSession(isTransacted, mode);
            Destination destination = session.createQueue(queueName);

            MessageProducer producer = session.createProducer(destination);

            TextMessage message = session.createTextMessage("Message for send");

            producer.send(message);

            LOG.info("Message has been sent success. Message contains that - '" +
            message.getText() + "'");

            //session.commit();
            session.close();
            connection.close();
        } catch (JMSException e) {
            LOG.error(".......................................................................");
            LOG.error(e.toString());
            LOG.error(".......................................................................");
        }
    }
}
