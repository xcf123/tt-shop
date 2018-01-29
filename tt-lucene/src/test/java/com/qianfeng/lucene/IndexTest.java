/*
package com.qianfeng.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndexTest {
    @Test
    public void testIndexCreated() throws IOException {
        BookDao bookDao=new BookDaoImpl();
        List<Book> list=bookDao.selectBookList();

        List<Document> documentList=new ArrayList<>();

        for (Book book:list){
            Document document=new Document();
            Field id = new StringField("id", book.getId().toString(), Field.Store.YES);
            //图书名称：要分词、要索引、要存储
            Field name = new TextField("name", book.getName(), Field.Store.YES);
            //图书价格：要分词、要索引、要存储
            Field price = new FloatField("price",book.getPrice(), Field.Store.YES);
            //图书图片地址：不分词、不索引、要存储
            Field pic = new StoredField("pic", book.getPic());
            //图片描述：要分词、要索引、要存储
            Field description = new TextField("description",book.getDescription(),Field.Store.YES);
            //将每一个Field添加文档中
            document.add(id);
            document.add(name);
            document.add(price);
            document.add(pic);
            document.add(description);
            //将每个document添加到文档域中
            documentList.add(document);
        }
        Analyzer analyzer=new StandardAnalyzer();
        IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);

        File file = new File("e:/bookindex");
        Directory directory = FSDirectory.open(file);
        IndexWriter writer=new IndexWriter(directory,config);
        for (Document document:documentList){
            writer.addDocument(document);
        }
        writer.close();

    }

    @Test
    public void testIndexSearch() throws ParseException, IOException {
        Analyzer analyzer=new StandardAnalyzer();

        QueryParser parser=new QueryParser("description",analyzer);
        Query parse = parser.parse("description:spring");
        File file = new File("e:/bookindex");
        Directory directory = FSDirectory.open(file);
        IndexReader reader= DirectoryReader.open(directory);
        IndexSearcher searcher=new IndexSearcher(reader);
        TopDocs topDocs= searcher.search(parse,10);
        System.out.println(topDocs.totalHits);
    }

    @Test
    public void testIndexDelete() throws IOException {
        Analyzer analyzer=new StandardAnalyzer();
        IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        File file = new File("e:/bookindex");
        Directory directory=FSDirectory.open(file);
        IndexWriter writer=new IndexWriter(directory,config);

//        Term term=new Term("description","spring");
//        writer.deleteDocuments(term);
        writer.deleteAll();
        writer.close();

    }
    @Test
    public void testUpdateIndex() throws Exception {
        Analyzer analyzer=new StandardAnalyzer();
        IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);

        File file = new File("e:/bookindex");
        Directory directory=FSDirectory.open(file);

        IndexWriter writer=new IndexWriter(directory,config);

        Term term=new Term("description","spring");

        Document document=new Document();
        document.add(new TextField("description","solr",Field.Store.YES));
        writer.updateDocument(term,document);
        writer.close();

    }
}
*/
