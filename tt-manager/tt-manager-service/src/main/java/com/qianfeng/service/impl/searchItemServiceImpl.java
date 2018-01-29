package com.qianfeng.service.impl;

import com.qianfeng.dao.SearchItemDao;
import com.qianfeng.dao.TbItemCustomMapper;
import com.qianfeng.pojo.vo.TbSearchItemCustom;
import com.qianfeng.pojo.vo.TbSearchItemResult;
import com.qianfeng.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class searchItemServiceImpl implements SearchItemService {
    @Autowired
    private TbItemCustomMapper tbItemCustomMapper;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchItemDao searchItemDao;
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public void importAllItems() {
        List<TbSearchItemCustom> list=new ArrayList<>();
        try {
            list=tbItemCustomMapper.getSearchItemList();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        for(TbSearchItemCustom tbSearchItemCustom:list){
            //拿到solr的文档域
            SolrInputDocument document = new SolrInputDocument();

            document.addField("id", tbSearchItemCustom.getId());
            document.addField("item_title", tbSearchItemCustom.getTitle());
            document.addField("item_sell_point", tbSearchItemCustom.getSellPoint());
            document.addField("item_price", tbSearchItemCustom.getPrice());
            document.addField("item_image", tbSearchItemCustom.getImage());
            document.addField("item_category_name", tbSearchItemCustom.getCatName());
            try {
                solrServer.add(document);
            } catch (SolrServerException e) {
                logger.error(e.getMessage(),e);
                e.printStackTrace();
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                e.printStackTrace();
            }
        }
        try {
            solrServer.commit();
        } catch (SolrServerException e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
    }

    @Override
    public TbSearchItemResult search(String keyword, int page, int i) {
        SolrQuery query=new SolrQuery();
        query.setQuery(keyword);
        query.setStart((page-1)*i);
        query.setRows(i);
        query.set("df","item_keywords");

        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");

        TbSearchItemResult searchResult =searchItemDao.search(query);
        int totalPage=(int) (searchResult.getRecordCount()+i-1)/i;
        searchResult.setTotalPages(totalPage);

        return searchResult;
    }

    @Override
    public TbSearchItemCustom searchItemById(long itemId) {
        TbSearchItemCustom item=new TbSearchItemCustom();
        try {
           item= tbItemCustomMapper.searchItemById(itemId);
            SolrInputDocument document=new SolrInputDocument();
            document.addField("id", item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSellPoint());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCatName());

            solrServer.add(document);

            solrServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
