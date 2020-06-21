/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: ftfXf6sQfPyedgI1LOovgJEVL9cBm0qK
 */
package com.igomall.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.service.PluginService;

/**
 * Controller - 存储插件
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminStoragePluginController")
@RequestMapping("/admin/storage_plugin")
public class StoragePluginController extends BaseController {

	@Resource
	private PluginService pluginService;

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("storagePlugins", pluginService.getStoragePlugins());
		return "admin/storage_plugin/list";
	}

}