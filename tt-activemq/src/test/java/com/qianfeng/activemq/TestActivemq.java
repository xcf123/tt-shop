/*
package com.qianfeng.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class TestActivemq {
    @Test
    public void queueTest() throws  Exception{
        //创建工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://106.14.165.2:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue("myfirstQueue");
        //创建生产者
        MessageProducer producer = session.createProducer(queue);
        //生成信息
        TextMessage message=session.createTextMessage("chu chu chu");
        //发送
        producer.send(message);
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void consumerTest() throws Exception{
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://106.14.165.2:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("myfirstQueue");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println(text);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
*/
