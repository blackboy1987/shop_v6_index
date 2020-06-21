
package com.igomall.dao.impl;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.igomall.dao.StorePluginStatusDao;
import com.igomall.entity.Store;
import com.igomall.entity.StorePluginStatus;

/**
 * Dao - 店铺插件状态
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Repository
public class StorePluginStatusDaoImpl extends BaseDaoImpl<StorePluginStatus, Long> implements StorePluginStatusDao {

	public StorePluginStatus find(Store store, String pluginId) {
		if (store == null || StringUtils.isEmpty(pluginId)) {
			return null;
		}
		try {
			String jpql = "select storePluginStatus from StorePluginStatus storePluginStatus where storePluginStatus.store = :store and storePluginStatus.pluginId = :pluginId";
			return entityManager.createQuery(jpql, StorePluginStatus.class).setParameter("store", store).setParameter("pluginId", pluginId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}