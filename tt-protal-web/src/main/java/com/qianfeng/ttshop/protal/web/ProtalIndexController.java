package com.qianfeng.ttshop.protal.web;

import com.qianfeng.pojo.po.TbContent;
import com.qianfeng.service.ContentService;
import com.qianfeng.utils.PropKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Scope("prototype")
public class ProtalIndexController {
    @Autowired
    private ContentService contentService;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String toProtalIndex(Model model){
        List<TbContent> list=null;
        try {
            Long cid= PropKit.use("picture.properties").getLong("categoryId");
            list=contentService.picListByCid(cid);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        model.addAttribute("ad1List",list);
        return "index";
    }

}
