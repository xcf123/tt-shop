package com.qianfeng.dao;

import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.po.TbItemParam;

import java.util.List;

public interface TbItemParamCustomMapper {
    TbItemParam selectParamByCid(Long id);

    List<TbItemParam> tbItemParamListByPage(Page page);

    Long tbItemParamCount();
}
