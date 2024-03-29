package com.piyougou.page.service;

public interface ItemPageService {

	/**
	 * 根据商品Id创建商品详情页
	 * @param goodsId
	 * @return
	 */
	public boolean genItemHtml(Long goodsId);
	/**
	 * 删除商品详情页
	 * @param goodsId
	 * @return
	 */
	public boolean deleteItemHtml(Long [] goodsIds);
}
