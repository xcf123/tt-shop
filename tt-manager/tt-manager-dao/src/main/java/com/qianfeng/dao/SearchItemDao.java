package com.qianfeng.dao;

import com.qianfeng.pojo.vo.TbSearchItemCustom;
import com.qianfeng.pojo.vo.TbSearchItemResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchItemDao {
    @Autowired
    private SolrServer solrServer;


    public TbSearchItemResult search(SolrQuery query) {
        TbSearchItemResult result=new TbSearchItemResult();
        try {
            QueryResponse response= solrServer.query(query);
            SolrDocumentList solrDocuments=response.getResults();
            long numFound = solrDocuments.getNumFound();
            result.setRecordCount(numFound);
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<TbSearchItemCustom> itemList = new ArrayList<>();
            for (SolrDocument solrDocument:solrDocuments){
                TbSearchItemCustom item = new TbSearchItemCustom();
                item.setId((String) solrDocument.get("id"));
                item.setCatName((String) solrDocument.get("item_category_name"));
                item.setImage((String) solrDocument.get("item_image"));
                item.setPrice((long) solrDocument.get("item_price"));
                item.setSellPoint((String) solrDocument.get("item_sell_point"));
                //取高亮显示
                List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
                String title = "";
                if (list != null && list.size() > 0) {
                    title = list.get(0);
                } else {
                    title = (String) solrDocument.get("item_title");
                }
                item.setTitle(title);
                //添加到商品列表
                itemList.add(item);
            }
            result.setItemList(itemList);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return result;
    }

}
