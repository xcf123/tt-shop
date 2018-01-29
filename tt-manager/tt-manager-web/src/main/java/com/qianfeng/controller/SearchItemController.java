package com.qianfeng.controller;

import com.qianfeng.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchItemController {
    @Autowired
    private SearchItemService service;
    @ResponseBody
    @RequestMapping("/search/item/import")
    public boolean importSearchItem(){
        try {
            service.importAllItems();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
