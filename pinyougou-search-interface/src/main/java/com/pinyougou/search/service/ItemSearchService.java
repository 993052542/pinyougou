package com.pinyougou.search.service;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {

	/**
	 * 关键字搜索方法  
	 * @param searchMap 
	 * @return
	 */
	public Map search(Map searchMap);
	/**
	 * 审核通过后更新索引库
	 * @param list
	 */
	public void importList(List list);
	
	/**
	 * 删除商品列表
	 * @param goodIds
	 */
	public void deleteByGoodsIds(List goodIds);
	
}
