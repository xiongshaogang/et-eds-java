package com.edaisong.api.activemq;

import java.util.Date;

import javax.jms.JMSException;   
import javax.jms.Message;   
import javax.jms.MessageListener;   
import javax.jms.TextMessage;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.edaisong.api.mongo.MongoService;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;

public class LogConsumerMessageListener implements MessageListener {   
    @Autowired
	private MongoTemplate mongoTemplate;
    @Autowired
    private MongoService mongoService;
    public void onMessage(Message message) {   
        try {   
            //这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换，或者直接把onMessage方法的参数改成Message的子类TextMessage  
            TextMessage textMsg = (TextMessage) message;  
            String mongoTable=getMongoTableName();
            mongoService.saveJsonInfo(mongoTable, textMsg.getText());
            //System.out.println("日志消费者接收到了一个日志消息，内容是：" + textMsg.getText());   
        } catch (JMSException e) {   
            e.printStackTrace();   
			String isSendMail = PropertyUtils.getProperty("IsSendMail");
			if (isSendMail.equals("1")) {
				String stackTrace = StringUtils.getStackTrace(e);
				SystemUtils.sendAlertEmail("Mongo_java项目预警", e.getMessage()+"\n"+stackTrace);
			}
        }   
    }
    private String getMongoTableName(){
    	return "logtb_"+ParseHelper.ToDateString(new Date(), "yyyy_MM");
    }
}  
