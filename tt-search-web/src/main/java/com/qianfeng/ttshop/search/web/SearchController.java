package com.qianfeng.ttshop.search.web;

import com.qianfeng.pojo.vo.TbSearchItemResult;
import com.qianfeng.service.SearchItemService;
import com.qianfeng.utils.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String searchItemList(String keyword, @RequestParam(defaultValue = "1") int page,
                                 Model model){
        if(StrKit.notBlank(keyword)){
            TbSearchItemResult result=searchItemService.search(keyword, page, 60);
            model.addAttribute("totalPages",result.getTotalPages());
            model.addAttribute("query",keyword);
            model.addAttribute("page",page);
            model.addAttribute("recordCount",result.getRecordCount());
            model.addAttribute("itemList",result.getItemList());
        }
        return "search";
    }
}
