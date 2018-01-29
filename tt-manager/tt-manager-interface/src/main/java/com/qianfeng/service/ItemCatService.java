package com.qianfeng.service;

import com.qianfeng.pojo.vo.TreeNode;

import java.util.List;

public interface ItemCatService {
    List<TreeNode> listCatsByParentId(Long parentId);
}
