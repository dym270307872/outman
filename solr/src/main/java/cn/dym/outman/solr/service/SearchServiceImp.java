package cn.dym.outman.solr.service;

import javax.naming.directory.SearchResult;

import org.apache.solr.client.solrj.SolrQuery;

import cn.dym.outman.solr.dao.SearchDao;

@Service
public class SearchServiceImp implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {
        //创建查询条件
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(queryString);
        //设置分页条件
        query.setStart((page-1)*rows);
        query.setRows(rows);
        //设置默认搜索域
        query.set("df", "item_title");
        //设置高亮
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
        query.setHighlightSimplePost("</font>");
        //query.setHighlightSimplePre("<font color='red'>");//标记，高亮关键字前缀
        //query.setHighlightSimplePost("</font>");//后缀
        //执行查询
        SearchResult searchResult = searchDao.search(query);
        //计算总页数
        Long recordCount = searchResult.getRecordCount();
        int pageCount = (int) (recordCount / rows);
        if (recordCount % rows > 0) {
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        searchResult.setCurPage(page);
        return searchResult;
    }

}