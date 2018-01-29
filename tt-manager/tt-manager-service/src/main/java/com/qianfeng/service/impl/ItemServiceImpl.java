package com.qianfeng.service.impl;


import com.qianfeng.dao.TbItemCustomMapper;
import com.qianfeng.dao.TbItemDescMapper;
import com.qianfeng.dao.TbItemMapper;
import com.qianfeng.dao.TbItemParamItemMapper;
import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.dto.PageTwo;
import com.qianfeng.pojo.dto.Result;
import com.qianfeng.pojo.po.TbItem;
import com.qianfeng.pojo.po.TbItemDesc;
import com.qianfeng.pojo.po.TbItemExample;
import com.qianfeng.pojo.po.TbItemParamItem;
import com.qianfeng.pojo.vo.Order;
import com.qianfeng.pojo.vo.TbItemCustom;
import com.qianfeng.pojo.vo.TbItemQuery;
import com.qianfeng.service.ItemService;
import com.qianfeng.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemCustomMapper customMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public List<TbItem> listItems() {
        return tbItemMapper.selectByExample(null);
    }

    @Override
    public TbItem findItemById(Long id) {
        return   tbItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery tbItemQuery) {
        Result<TbItemCustom> result=new Result<>();
        Long total=customMapper.selectCount(tbItemQuery);

        result.setTotal(total);
        result.setRows(customMapper.selectListItemsByPage(page,order,tbItemQuery));
        return result;
    }

    @Override
    public Result<TbItem> listItemsByPageTwo(PageTwo page) {
        Result<TbItem> result=new Result<>();
       // Long total=customMapper.selectCount();

        result.setTotal(964l);
        result.setRows(customMapper.selectListItemsByPageTwo(page));
        return result;
    }

    @Override
    public int batchUpdate(List<Long> ids,int flag) {
        int i=0;
        try {
            TbItem tbItem=new TbItem();
            switch (flag){
                case 1:
                    tbItem.setStatus((byte)1);
                    break;
                case 2:
                    tbItem.setStatus((byte)2);
                    break;
                case 3:
                    tbItem.setStatus((byte)3);
                    break;
            }


            TbItemExample example=new TbItemExample();
            TbItemExample.Criteria criteria=example.createCriteria();
            criteria.andIdIn(ids);

            i=tbItemMapper.updateByExampleSelective(tbItem,example);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return i;
    }

    @Transactional
    @Override
    public Long saveItem(TbItem item, String itemDesc, String paramData) {
        Long itemId=null;
        try {
            //存放两张表
            //tb_item
             itemId = IDUtils.getItemId();
            item.setId(itemId);
            item.setStatus((byte)1);
            item.setCreated(new Date());
            item.setUpdated(new Date());
            int i = tbItemMapper.insert(item);
            //tb_item_desc
            TbItemDesc tbItemDesc = new TbItemDesc();
            tbItemDesc.setItemId(itemId);
            tbItemDesc.setItemDesc(itemDesc);
            tbItemDesc.setCreated(new Date());
            tbItemDesc.setUpdated(new Date());
            i += itemDescMapper.insert(tbItemDesc);
            //tb_item_param_item
            TbItemParamItem tbItemParamItem = new TbItemParamItem();
            tbItemParamItem.setItemId(itemId);
            tbItemParamItem.setParamData(paramData);
            tbItemParamItem.setCreated(new Date());
            tbItemParamItem.setUpdated(new Date());
            i += itemParamItemMapper.insert(tbItemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemId;

    }

}
