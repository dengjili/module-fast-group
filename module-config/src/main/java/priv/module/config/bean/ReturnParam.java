package priv.module.config.bean;

import java.util.List;

public class ReturnParam<T> {
	List<T> list;
	PageParams page;
	
	public ReturnParam() {
	}
	
	public ReturnParam(PageParams params) {
		page = params;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public PageParams getPage() {
		return page;
	}
	public void setPage(PageParams page) {
		this.page = page;
	}
}
