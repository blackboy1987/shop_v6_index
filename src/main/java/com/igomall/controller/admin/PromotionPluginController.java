/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: aPVqnDbJerrxvgB/6xPozSmXasBjUSOV
 */
package com.igomall.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.service.PluginService;

/**
 * Controller - 促销插件
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminPromotionPluginController")
@RequestMapping("/admin/promotion_plugin")
public class PromotionPluginController extends BaseController {

	@Resource
	private PluginService pluginService;

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("promotionPlugins", pluginService.getPromotionPlugins());
		return "admin/promotion_plugin/list";
	}

}