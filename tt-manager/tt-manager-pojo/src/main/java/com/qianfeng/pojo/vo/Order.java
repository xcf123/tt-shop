package com.qianfeng.pojo.vo;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String sort;
    private String order;

    public List<String> getOrderParams() {
        String[] sort=this.sort.split(",");
        String[] order=this.order.split(",");
        List<String> orderParams=new ArrayList<>();
        for (int i=0;i<sort.length;i++){
            orderParams.add(sort[i]+" "+order[i]);
        }
        return orderParams;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
