
package com.igomall.dao;

import java.util.List;

import com.igomall.entity.FriendLink;

/**
 * Dao - 友情链接
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface FriendLinkDao extends BaseDao<FriendLink, Long> {

	/**
	 * 查找友情链接
	 * 
	 * @param type
	 *            类型
	 * @return 友情链接
	 */
	List<FriendLink> findList(FriendLink.Type type);

}