package com.qianfeng.service.impl;

import com.qianfeng.dao.TbContentMapper;
import com.qianfeng.jedis.JedisClient;
import com.qianfeng.pojo.po.TbContent;
import com.qianfeng.pojo.po.TbContentExample;
import com.qianfeng.service.ContentService;
import com.qianfeng.utils.JsonUtils;
import com.qianfeng.utils.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private  JedisClient jedisClient;
    @Autowired
    private TbContentMapper tbContentMapper;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public List<TbContent> picListByCid(Long cid) {
        try {
            String json= jedisClient.hget("CONTENT_LIST",Long.toString(cid));
            if(StrKit.notBlank(json)){
                List<TbContent> list= JsonUtils.jsonToList(json,TbContent.class);
                return list;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);

        List<TbContent> list=tbContentMapper.selectByExample(example);

        try {
            jedisClient.hset("CONTENT_LIST",Long.toString(cid),JsonUtils.objectToJson(list));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return list;
    }
}
