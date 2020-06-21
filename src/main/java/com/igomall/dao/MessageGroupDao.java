
package com.igomall.dao;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.entity.MessageGroup;
import com.igomall.entity.User;

/**
 * Dao - 消息组
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface MessageGroupDao extends BaseDao<MessageGroup, Long> {

	/**
	 * 查找
	 * 
	 * @param user
	 *            用户
	 * @param pageable
	 *            分页信息
	 * @return 消息组分页
	 */
	Page<MessageGroup> findPage(User user, Pageable pageable);

	/**
	 * 查找
	 * 
	 * @param user1
	 *            用户1
	 * @param user2
	 *            用户2
	 * @return 消息组
	 */
	MessageGroup find(User user1, User user2);

}