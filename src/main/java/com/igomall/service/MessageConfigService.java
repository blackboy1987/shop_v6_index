
package com.igomall.service;

import com.igomall.entity.MessageConfig;

/**
 * Service - 消息配置
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface MessageConfigService extends BaseService<MessageConfig, Long> {

	/**
	 * 查找消息配置
	 * 
	 * @param type
	 *            类型
	 * @return 消息配置
	 */
	MessageConfig find(MessageConfig.Type type);

}