package cn.dym.outman.solr.controller;

import java.io.UnsupportedEncodingException;

import javax.naming.directory.SearchResult;

import com.ctc.wstx.util.ExceptionUtil;

import cn.dym.outman.solr.service.SearchService;

public class SearchController {

	@Autowired
    private SearchService searchService;
    
	
	
	@RequestMapping("/q")
    @ResponseBody
    public TaotaoResult search(@RequestParam(defaultValue = "")String keyword,
                               @RequestParam(defaultValue = "1")Integer page,
                               @RequestParam(defaultValue = "30")Integer rows) {
        //转字符串
        try {
            keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SearchResult searchResult = null;
        try {
            searchResult = searchService.search(keyword,page,rows);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok(searchResult);
    }
}
