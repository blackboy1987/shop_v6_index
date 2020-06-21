
package com.igomall.dao;

import java.util.List;

import com.igomall.entity.Message;
import com.igomall.entity.MessageGroup;
import com.igomall.entity.User;

/**
 * Dao - 消息
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface MessageDao extends BaseDao<Message, Long> {

	/**
	 * 查找
	 * 
	 * @param messageGroup
	 *            消息组
	 * @param user
	 *            用户
	 * @return 消息
	 */
	List<Message> findList(MessageGroup messageGroup, User user);

	/**
	 * 未读消息数量
	 * 
	 * @param messageGroup
	 *            消息组
	 * @param user
	 *            用户
	 * @return 未读消息数量
	 */
	Long unreadMessageCount(MessageGroup messageGroup, User user);

}