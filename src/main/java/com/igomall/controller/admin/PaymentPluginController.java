/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: pny8ZAbRhJ7OUqdBKiIp/KRwihJWMusw
 */
package com.igomall.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.service.PluginService;

/**
 * Controller - 支付插件
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminPaymentPluginController")
@RequestMapping("/admin/payment_plugin")
public class PaymentPluginController extends BaseController {

	@Resource
	private PluginService pluginService;

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("paymentPlugins", pluginService.getPaymentPlugins());
		return "admin/payment_plugin/list";
	}

}