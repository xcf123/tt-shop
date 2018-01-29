package com.qianfeng.service;

import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.dto.Result;
import com.qianfeng.pojo.po.TbItemParam;

public interface ItemCatParamService {
    TbItemParam getParamById(Long id);

    Result<TbItemParam> tbItemParamListByPage(Page page);
}
