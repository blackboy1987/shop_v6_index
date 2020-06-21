
package com.igomall.service;

import com.igomall.entity.Distributor;
import com.igomall.entity.Member;

/**
 * Service - 分销员
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface DistributorService extends BaseService<Distributor, Long> {

	/**
	 * 创建
	 * 
	 * @param member
	 *            会员
	 */
	void create(Member member);

	/**
	 * 创建
	 * 
	 * @param member
	 *            会员
	 * @param spreadMember
	 *            推广会员
	 */
	void create(Member member, Member spreadMember);

	/**
	 * 修改
	 * 
	 * @param distributor
	 *            分销员
	 * @param spreadMember
	 *            推广会员
	 */
	void modify(Distributor distributor, Member spreadMember);

}