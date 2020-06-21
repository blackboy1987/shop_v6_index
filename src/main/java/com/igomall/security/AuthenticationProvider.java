
package com.igomall.security;

import com.igomall.entity.User;

import java.util.Set;

/**
 * Security - 认证Provider
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface AuthenticationProvider {

	/**
	 * 获取用户
	 * 
	 * @param principal
	 *            身份
	 * @return 用户
	 */
	User getUser(Object principal);

	/**
	 * 获取权限
	 * 
	 * @param user
	 *            用户
	 * @return 权限
	 */
	Set<String> getPermissions(User user);

	/**
	 * 是否支持
	 * 
	 * @param userClass
	 *            用户类型
	 * @return 是否支持
	 */
	boolean supports(Class<?> userClass);

}