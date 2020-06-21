/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: njpD3cy0QBwx+WVVSQaZ274C4FkjLGRw
 */
package com.igomall.controller.admin.plugin;

import java.math.BigDecimal;
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
import com.igomall.plugin.PaymentPlugin;
import com.igomall.plugin.WeixinH5PaymentPlugin;
import com.igomall.service.PluginConfigService;

/**
 * Controller - 微信支付(H5支付)
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminPluginWeixinH5PaymentController")
@RequestMapping("/admin/plugin/weixin_h5_payment")
public class WeixinH5PaymentController extends BaseController {

	@Resource
	private WeixinH5PaymentPlugin weixinH5PaymentPlugin;
	@Resource
	private PluginConfigService pluginConfigService;

	/**
	 * 安装
	 */
	@PostMapping("/install")
	public ResponseEntity<?> install() {
		if (!weixinH5PaymentPlugin.getIsInstalled()) {
			PluginConfig pluginConfig = new PluginConfig();
			pluginConfig.setPluginId(weixinH5PaymentPlugin.getId());
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
		if (weixinH5PaymentPlugin.getIsInstalled()) {
			pluginConfigService.deleteByPluginId(weixinH5PaymentPlugin.getId());
		}
		return Results.OK;
	}

	/**
	 * 设置
	 */
	@GetMapping("/setting")
	public String setting(ModelMap model) {
		PluginConfig pluginConfig = weixinH5PaymentPlugin.getPluginConfig();
		model.addAttribute("feeTypes", PaymentPlugin.FeeType.values());
		model.addAttribute("pluginConfig", pluginConfig);
		return "/admin/plugin/weixin_h5_payment/setting";
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public ResponseEntity<?> update(String displayName, String appId, String mchId, String apiKey, PaymentPlugin.FeeType feeType, BigDecimal fee, String logo, String description, @RequestParam(defaultValue = "false") Boolean isEnabled, Integer order) {
		PluginConfig pluginConfig = weixinH5PaymentPlugin.getPluginConfig();
		Map<String, String> attributes = new HashMap<>();
		attributes.put(PaymentPlugin.DISPLAY_NAME_ATTRIBUTE_NAME, displayName);
		attributes.put("appId", appId);
		attributes.put("mchId", mchId);
		attributes.put("apiKey", apiKey);
		attributes.put(PaymentPlugin.FEE_TYPE_ATTRIBUTE_NAME, String.valueOf(feeType));
		attributes.put(PaymentPlugin.FEE_ATTRIBUTE_NAME, String.valueOf(fee));
		attributes.put(PaymentPlugin.LOGO_ATTRIBUTE_NAME, logo);
		attributes.put(PaymentPlugin.DESCRIPTION_ATTRIBUTE_NAME, description);
		pluginConfig.setAttributes(attributes);
		pluginConfig.setIsEnabled(isEnabled);
		pluginConfig.setOrder(order);
		pluginConfigService.update(pluginConfig);
		return Results.OK;
	}

}