package com.qianfeng.controller;

import com.qianfeng.pojo.vo.TreeNode;
import com.qianfeng.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Scope("prototype")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping(value = "/itemCat", method = RequestMethod.POST)
    public List<TreeNode> listCatsByParentId(@RequestParam("parentId") Long parentId) {
        List<TreeNode> list = null;
        try {
            list = itemCatService.listCatsByParentId(parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
