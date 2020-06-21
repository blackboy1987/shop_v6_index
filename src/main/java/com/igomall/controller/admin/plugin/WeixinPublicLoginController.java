/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: 20nEXek7boc6laABLswss6ttgaDByHeK
 */
package com.igomall.controller.admin.plugin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.igomall.Results;
import com.igomall.controller.admin.BaseController;
import com.igomall.entity.PluginConfig;
import com.igomall.plugin.LoginPlugin;
import com.igomall.plugin.WeixinPublicLoginPlugin;
import com.igomall.service.PluginConfigService;

/**
 * Controller - 微信登录(公众号登录)
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminPluginWeixinPublicLoginController")
@RequestMapping("/admin/plugin/weixin_public_login")
public class WeixinPublicLoginController extends BaseController {

	@Resource
	private WeixinPublicLoginPlugin weixinPublicLoginPlugin;
	@Resource
	private PluginConfigService pluginConfigService;

	/**
	 * 安装
	 */
	@PostMapping("/install")
	public ResponseEntity<?> install() {
		if (!weixinPublicLoginPlugin.getIsInstalled()) {
			PluginConfig pluginConfig = new PluginConfig();
			pluginConfig.setPluginId(weixinPublicLoginPlugin.getId());
			pluginConfig.setIsEnabled(false);
			pluginConfig.setAttributes(null);
			pluginConfigService.save(pluginConfig);
		}
		return Results.OK;
	}

	/**
	 * 卸载
	 */
	@PostMapping("/uninstall")
	public ResponseEntity<?> uninstall() {
		if (weixinPublicLoginPlugin.getIsInstalled()) {
			pluginConfigService.deleteByPluginId(weixinPublicLoginPlugin.getId());
		}
		return Results.OK;
	}

	/**
	 * 设置
	 */
	@GetMapping("/setting")
	public String setting(ModelMap model) {
		PluginConfig pluginConfig = weixinPublicLoginPlugin.getPluginConfig();
		model.addAttribute("pluginConfig", pluginConfig);
		return "/admin/plugin/weixin_public_login/setting";
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public ResponseEntity<?> update(String displayName, String appId, String appSecret, String logo, String description, @RequestParam(defaultValue = "false") Boolean isEnabled, Integer order) {
		PluginConfig pluginConfig = weixinPublicLoginPlugin.getPluginConfig();
		Map<String, String> attributes = new HashMap<>();
		attributes.put(LoginPlugin.DISPLAY_NAME_ATTRIBUTE_NAME, displayName);
		attributes.put("appId", appId);
		attributes.put("appSecret", appSecret);
		attributes.put(LoginPlugin.LOGO_ATTRIBUTE_NAME, logo);
		attributes.put(LoginPlugin.DESCRIPTION_ATTRIBUTE_NAME, description);
		pluginConfig.setAttributes(attributes);
		pluginConfig.setIsEnabled(isEnabled);
		pluginConfig.setOrder(order);
		pluginConfigService.update(pluginConfig);
		return Results.OK;
	}

}