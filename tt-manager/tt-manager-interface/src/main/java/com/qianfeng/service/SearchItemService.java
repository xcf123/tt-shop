package com.qianfeng.service;

import com.qianfeng.pojo.vo.TbSearchItemCustom;
import com.qianfeng.pojo.vo.TbSearchItemResult;


public interface SearchItemService {
    void importAllItems();

    TbSearchItemResult search(String keyword, int page, int i);

    TbSearchItemCustom searchItemById(long itemId);
}
