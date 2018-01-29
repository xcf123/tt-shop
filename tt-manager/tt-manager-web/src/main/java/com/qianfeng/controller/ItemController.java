package com.qianfeng.controller;

import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.dto.Result;
import com.qianfeng.pojo.po.TbItem;
import com.qianfeng.pojo.vo.Order;
import com.qianfeng.pojo.vo.TbItemCustom;
import com.qianfeng.pojo.vo.TbItemQuery;
import com.qianfeng.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.List;

@Controller
@Scope("prototype")
public class ItemController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemService itemService;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource
    private Destination topicDestination;

    @ResponseBody
    @RequestMapping(value = "/items")
    public Result<TbItemCustom> Items(Page page, Order order, TbItemQuery tbItemQuery){
        Result<TbItemCustom> result=null;

        try {

            result=itemService.listItemsByPage(page,order,tbItemQuery);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return  result;
    }


    @ResponseBody
    @RequestMapping(value = "/items/batch",method = RequestMethod.POST)
    public  int batchUpdate(@RequestParam("ids[]") List<Long> ids,int flag){
        int i=0;
        try {
           i=itemService.batchUpdate(ids,flag);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }


    @ResponseBody
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public Long saveItem(TbItem item, String itemDesc, String paramData) {

        try {
           final Long itemId = itemService.saveItem(item, itemDesc, paramData);
            if(itemId!=null){
                jmsTemplate.send(topicDestination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage message = session.createTextMessage(itemId.toString());
                        return message;
                    }
                });
            }
            return itemId;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
}
