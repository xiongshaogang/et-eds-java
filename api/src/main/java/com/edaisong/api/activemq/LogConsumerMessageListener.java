package com.edaisong.api.activemq;

import javax.jms.JMSException;   
import javax.jms.Message;   
import javax.jms.MessageListener;   
import javax.jms.TextMessage;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.edaisong.api.mongo.MongoService;

public class LogConsumerMessageListener implements MessageListener {   
    @Autowired
	private MongoTemplate mongoTemplate;
    @Autowired
    private MongoService mongoService;
    public void onMessage(Message message) {   
        try {   
            //这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换，或者直接把onMessage方法的参数改成Message的子类TextMessage  
            TextMessage textMsg = (TextMessage) message;   
            mongoService.saveJsonInfo("logtb", textMsg.getText());
            //System.out.println("日志消费者接收到了一个日志消息，内容是：" + textMsg.getText());   
        } catch (JMSException e) {   
            e.printStackTrace();   
        }   
    }   
}  