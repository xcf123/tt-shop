package com.qianfeng.controller;

import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.dto.Result;
import com.qianfeng.pojo.po.TbItemParam;
import com.qianfeng.service.ItemCatParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class ItemCatParamController {
    @Autowired
    private ItemCatParamService itemCatParamService;

    @ResponseBody
    @RequestMapping(value = "/itemParam",method = RequestMethod.POST)
    public TbItemParam getParam(@RequestParam("id") Long id){
        TbItemParam param=null;
        try {
            param=itemCatParamService.getParamById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  param;
    }

    @ResponseBody
    @RequestMapping("/itemParams")
    public Result<TbItemParam> tbItemParamListByPage(Page page){
        Result<TbItemParam> result=null;
        try {
            result=itemCatParamService.tbItemParamListByPage(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }

}
