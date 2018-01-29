package com.qianfeng.service.impl;

import com.qianfeng.dao.TbItemParamCustomMapper;
import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.dto.Result;
import com.qianfeng.pojo.po.TbItemParam;
import com.qianfeng.service.ItemCatParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCatParamServiceImpl implements ItemCatParamService {
    @Autowired
    private TbItemParamCustomMapper paramCustomMapper;

    @Override
    public TbItemParam getParamById(Long id) {
        TbItemParam param=null;

        try {
            param=paramCustomMapper.selectParamByCid(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }

    @Override
    public Result<TbItemParam> tbItemParamListByPage(Page page) {
        Result<TbItemParam> result=new Result<>();
        try {
           Long total=paramCustomMapper.tbItemParamCount();
           result.setTotal(total);
            result.setRows(paramCustomMapper.tbItemParamListByPage(page));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
