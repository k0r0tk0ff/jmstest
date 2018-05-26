package ru.k0r0tk0ff;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;


public class Sender {

    private static final Logger LOG  = LoggerFactory.getLogger(Sender.class);

    public void sendMessage() {

        //Name of queue
        String queueName = "queue";

        ConnectionFactory f = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "failover://tcp://localhost:61616");

        try {
            Connection connection = f.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);

            MessageProducer producer = session.createProducer(destination);

            TextMessage message = session.createTextMessage("Message for send");

            producer.send(message);

            if(LOG.isDebugEnabled()) {
                LOG.debug(".............................Message has been sent success. Message contains that - '" +
                        message.getText() + "'");
            }

            connection.close();

            // for debug intercept and log exception
            //throw new JMSException(" Exception Message ");

        } catch (JMSException e) {
            LOG.error(".......................................................................");
            LOG.error(e.toString());
            LOG.error(".......................................................................");
        }
    }
}
