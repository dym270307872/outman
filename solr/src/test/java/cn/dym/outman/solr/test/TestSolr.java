package cn.dym.outman.solr.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class TestSolr {



@Test
public void testSolrJ() {
    //创建连接
    SolrServer solrServer = new HttpSolrServer("http://192.168.6.57:8983/solr/core2");
    //创建一个文档对象
    SolrInputDocument document = new SolrInputDocument();
    //添加域
    document.addField("id","solrtest01");
    document.addField("item_title","测试商品");
    document.addField("item_sell_point","买点");
    //添加到索引库
    try {
        solrServer.add(document);
        //提交
        solrServer.commit();
    } catch (SolrServerException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@Test
public void testQuery(){
    SolrServer solrServer = new HttpSolrServer("http://192.168.6.57:8983/solr/core2");
    //创建一查询对象
    SolrQuery query = new SolrQuery();
    query.setQuery("*:*");
    //执行查询
    try {
        QueryResponse response = solrServer.query(query);
        SolrDocumentList solrDocumentsList = response.getResults();
        for (SolrDocument solrdocument:solrDocumentsList){
            System.out.println(solrdocument.get("id"));
            System.out.println(solrdocument.get("item_title"));
            System.out.println(solrdocument.get("item_sell_point"));
        }
    } catch (SolrServerException e) {
        e.printStackTrace();
    }
}

}
