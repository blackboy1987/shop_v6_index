/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: ZkQjQo/sJci57ndZjubXRCtUsKW8sY+j
 */
package com.igomall.service;

import java.util.List;

import com.igomall.entity.ParameterValue;

/**
 * Service - 参数值
 * 
 * @author 好源++ Team
 * @version 6.1
 */
public interface ParameterValueService {

	/**
	 * 参数值过滤
	 * 
	 * @param parameterValues
	 *            参数值
	 */
	void filter(List<ParameterValue> parameterValues);

}