package cn.dym.outman.solr.service;

import javax.naming.directory.SearchResult;

public interface SearchService {

	public SearchResult search(String queryString, int page, int rows) throws Exception;
	
}
