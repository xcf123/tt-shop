package com.qianfeng.dao;


import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.dto.PageTwo;
import com.qianfeng.pojo.po.TbItem;
import com.qianfeng.pojo.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemCustomMapper {


    Long selectCount(@Param("tbItemQuery")TbItemQuery tbItemQuery);

    List<TbItemCustom> selectListItemsByPage(@Param("page") Page page, @Param("order") Order order,@Param("tbItemQuery") TbItemQuery tbItemQuery);

    List<TbItem> selectListItemsByPageTwo(PageTwo page);

    List<TbSearchItemCustom> getSearchItemList();

    TbSearchItemCustom searchItemById(long itemId);
}
