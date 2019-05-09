package cn.dym.outman.freemaker.bean;

import java.util.List;
import java.util.Map;

public class PdfData {
	
	private Map<String,Object> obj;
	
	private Map<String,Object> bizType;
	
	private Map<String,Object> jbxx;
	
	private List<Map<String,Object>> files;

	public Map<String, Object> getObj() {
		return obj;
	}

	public void setObj(Map<String, Object> obj) {
		this.obj = obj;
	}

	public Map<String, Object> getBizType() {
		return bizType;
	}

	public void setBizType(Map<String, Object> bizType) {
		this.bizType = bizType;
	}

	public Map<String, Object> getJbxx() {
		return jbxx;
	}

	public void setJbxx(Map<String, Object> jbxx) {
		this.jbxx = jbxx;
	}

	public List<Map<String, Object>> getFiles() {
		return files;
	}

	public void setFiles(List<Map<String, Object>> files) {
		this.files = files;
	}
	
	
	
}
