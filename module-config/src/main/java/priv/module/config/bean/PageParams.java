package priv.module.config.bean;

public class PageParams {
	// 当前页码
	private Integer page;
	// 每页数量
	private Integer pageSize;
	// 总数量
	private Integer total;
	// 总页数
	private Integer pageTotal;

	// 是否启动插件（开关）
	private Boolean useFlag;
	// 是否检测页码下标的有效性
	private Boolean checkFlag;
	// 是否清楚order by后面的语句
	private Boolean cleanOrderBy;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Boolean getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Boolean useFlag) {
		this.useFlag = useFlag;
	}

	public Boolean getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Boolean getCleanOrderBy() {
		return cleanOrderBy;
	}

	public void setCleanOrderBy(Boolean cleanOrderBy) {
		this.cleanOrderBy = cleanOrderBy;
	}
}
