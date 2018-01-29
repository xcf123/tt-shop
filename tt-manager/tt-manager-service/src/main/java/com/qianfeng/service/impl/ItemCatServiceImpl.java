package com.qianfeng.service.impl;

import com.qianfeng.dao.TbItemCatMapper;
import com.qianfeng.pojo.po.TbItemCat;
import com.qianfeng.pojo.po.TbItemCatExample;
import com.qianfeng.pojo.vo.TreeNode;
import com.qianfeng.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TreeNode> listCatsByParentId(Long parentId) {
        List<TreeNode> list=new ArrayList<>();
        try {
            //创建查询模板
            TbItemCatExample example = new TbItemCatExample();
            TbItemCatExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(parentId);
            //执行查询
            List<TbItemCat> itemCatList = tbItemCatMapper.selectByExample(example);
            //List<TbItemCat> ----> List<TreeNode>
            for (TbItemCat cat : itemCatList) {
                TreeNode tn = new TreeNode();
                tn.setId(cat.getId());
                tn.setText(cat.getName());
                tn.setState(cat.getIsParent() ? "closed" : "open");
                list.add(tn);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }
}
