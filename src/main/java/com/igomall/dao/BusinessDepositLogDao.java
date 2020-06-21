
package com.igomall.dao;

import java.math.BigDecimal;
import java.util.Date;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.entity.Business;
import com.igomall.entity.BusinessDepositLog;

/**
 * Dao - 商家预存款记录
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface BusinessDepositLogDao extends BaseDao<BusinessDepositLog, Long> {

	/**
	 * 查找商家预存款记录分页
	 * 
	 * @param business
	 *            商家
	 * @param pageable
	 *            分页信息
	 * @return 商家预存款记录分页
	 */
	Page<BusinessDepositLog> findPage(Business business, Pageable pageable);

	/**
	 * 查询提现总额
	 * 
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return 提现总额
	 */
	BigDecimal cashTotalAmount(Date beginDate, Date endDate);

}