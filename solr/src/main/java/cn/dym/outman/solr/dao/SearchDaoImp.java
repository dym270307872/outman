package cn.dym.outman.solr.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.directory.SearchResult;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

@Repository
public class SearchDaoImp implements SearchDao {

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {
        //执行查询
        QueryResponse response = solrServer.query(query);
        //取查询结果列表
        SolrDocumentList solrDocumentList = response.getResults();
        List<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList) {
            //创建一个SearchItem对象
            SearchItem item = new SearchItem();
            item.setNameCategoryName((String) solrDocument.get("item_category_name"));
            item.setId((String) solrDocument.get("id"));
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((Long) solrDocument.get("item_price"));
            item.setSellPoint((String) solrDocument.get("item_sell_point"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            if (list != null && list.size() > 0) {
                //取高亮后的结果
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            item.setTitle(itemTitle);
            //添加到列表
            itemList.add(item);
        }
        SearchResult result = new SearchResult();
        result.setItemList(itemList);
        //查询结果总数量
        result.setRecordCount(solrDocumentList.getNumFound());
        return result;
    }

}
