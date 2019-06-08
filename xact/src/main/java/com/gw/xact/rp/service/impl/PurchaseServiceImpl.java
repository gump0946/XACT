package com.gw.xact.rp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.gw.xact.rp.dao.ProductDao;
import com.gw.xact.rp.dao.PurchaseRecordDao;
import com.gw.xact.rp.pojo.ProductPo;
import com.gw.xact.rp.pojo.PurchaseRecordPo;
import com.gw.xact.rp.service.PurchaseService;

/**** imports ****/
@Service
public class PurchaseServiceImpl implements PurchaseService {
	@Autowired
	private ProductDao productDao = null;
	@Autowired
	private PurchaseRecordDao purchaseRecordDao = null;
	/*
	 * @Override // 启动Spring数据库事务机制
	 * 
	 * @Transactional public boolean purchase(Long userId, Long productId, int
	 * quantity) { // 获取产品 ProductPo product = productDao.getProduct(productId);
	 * // 比较库存和购买数量 if (product.getStock() < quantity) { // 库存不足 return false; }
	 * // 扣减库存 productDao.decreaseProduct(productId, quantity); // 初始化购买记录
	 * PurchaseRecordPo pr = this.initPurchaseRecord(userId, product, quantity);
	 * // 插入购买记录 purchaseRecordDao.insertPurchaseRecord(pr); return true; }
	 */
	// @Override
	// // 启动Spring数据库事务机制
	// @Transactional
	// public boolean purchase(Long userId, Long productId, int quantity) {
	// // 获取产品（线程旧值）
	// ProductPo product = productDao.getProduct(productId);
	// // 比较库存和购买数量
	// if (product.getStock() < quantity) {
	// // 库存不足
	// return false;
	// }
	// // 获取当前版本号
	// int version = product.getVersion();
	// // 扣减库存,同时将当前版本号发送给后台去比较
	// int result = productDao.decreaseProduct(productId, quantity, version);
	// // 如果更新数据失败，说明数据在多线程中被其他线程修改，导致失败返回
	// if (result == 0) {
	// return false;
	// }
	// // 初始化购买记录
	// PurchaseRecordPo pr = this.initPurchaseRecord(userId, product, quantity);
	// // 插入购买记录
	// purchaseRecordDao.insertPurchaseRecord(pr);
	// return true;
	// }

	@Override
	// 启动Spring数据库事务机制
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public boolean purchase(Long userId, Long productId, int quantity) {
		// 当前时间
		long start = System.currentTimeMillis();
		// 循环尝试直至成功
		while (true) {
			// 循环时间
			long end = System.currentTimeMillis();
			// 如果循环时间大于100毫秒返回终止循环
			if (end - start > 100) {
				return false;
			}
			// 获取产品
			ProductPo product = productDao.getProduct(productId);
			// 获取当前版本号
			int version = product.getVersion();
			// 比较库存和购买数量
			if (product.getStock() < quantity) {
				// 库存不足
				return false;
			}

			// 扣减库存,同时将当前版本号发送给后台去比较
			int result = productDao.decreaseProduct(productId, quantity, version);
			// 如果更新数据失败，说明数据在多线程中被其他线程修改，
			// 导致失败，则通过循环重入尝试购买商品
			if (result == 0) {
				continue;
			}
			// 初始化购买记录
			PurchaseRecordPo pr = this.initPurchaseRecord(userId, product, quantity);
			// 插入购买记录
			purchaseRecordDao.insertPurchaseRecord(pr);
			return true;
		}
	}

	// 初始化购买信息
	private PurchaseRecordPo initPurchaseRecord(Long userId, ProductPo product, int quantity) {
		PurchaseRecordPo pr = new PurchaseRecordPo();
		pr.setNote("purchase time: " + System.currentTimeMillis());
		pr.setPrice(product.getPrice());
		pr.setProductId(product.getId());
		pr.setQuantity(quantity);
		double sum = product.getPrice() * quantity;
		pr.setSum(sum);
		pr.setUserId(userId);
		return pr;
	}

}