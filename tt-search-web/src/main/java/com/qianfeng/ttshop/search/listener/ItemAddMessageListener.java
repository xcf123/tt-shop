package com.qianfeng.ttshop.search.listener;

import com.qianfeng.pojo.vo.TbSearchItemCustom;
import com.qianfeng.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ItemAddMessageListener implements MessageListener{
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchItemService searchItemService;

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage=(TextMessage) message;
            String text = textMessage.getText();
            TbSearchItemCustom item=searchItemService.searchItemById(Long.parseLong(text));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
