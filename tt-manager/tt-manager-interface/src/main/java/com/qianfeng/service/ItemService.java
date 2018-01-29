package com.qianfeng.service;

import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.dto.PageTwo;
import com.qianfeng.pojo.dto.Result;
import com.qianfeng.pojo.po.TbItem;
import com.qianfeng.pojo.vo.Order;
import com.qianfeng.pojo.vo.TbItemCustom;
import com.qianfeng.pojo.vo.TbItemQuery;

import java.util.List;

public interface ItemService {
    public TbItem findItemById(Long id);

    List<TbItem> listItems();

    Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery tbItemQuery);

    Result<TbItem> listItemsByPageTwo(PageTwo page);

    int batchUpdate(List<Long> ids,int flag);

    Long saveItem(TbItem item, String itemDesc, String paramData);
}
