package cn.dym.outman.solr.dao;

import javax.naming.directory.SearchResult;

import org.apache.solr.client.solrj.SolrQuery;

public interface SearchDao {

	
	public SearchResult search(SolrQuery query) throws Exception;
	
	
}
