/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: WBIkzW/wlfDvdiwIXXIQR9vrlpAO6xfT
 */
package com.igomall.controller.admin;

import java.util.HashSet;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.Pageable;
import com.igomall.Results;
import com.igomall.entity.ShippingMethod;
import com.igomall.service.DeliveryCorpService;
import com.igomall.service.PaymentMethodService;
import com.igomall.service.ShippingMethodService;

/**
 * Controller - 配送方式
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminShippingMethodController")
@RequestMapping("/admin/shipping_method")
public class ShippingMethodController extends BaseController {

	@Resource
	private ShippingMethodService shippingMethodService;
	@Resource
	private PaymentMethodService paymentMethodService;
	@Resource
	private DeliveryCorpService deliveryCorpService;

	/**
	 * 添加
	 */
	@GetMapping("/add")
	public String add(ModelMap model) {
		model.addAttribute("deliveryCorps", deliveryCorpService.findAll());
		model.addAttribute("paymentMethods", paymentMethodService.findAll());
		return "admin/shipping_method/add";
	}

	/**
	 * 保存
	 */
	@PostMapping("/save")
	public ResponseEntity<?> save(ShippingMethod shippingMethod, Long defaultDeliveryCorpId, Long[] paymentMethodIds) {
		shippingMethod.setDefaultDeliveryCorp(deliveryCorpService.find(defaultDeliveryCorpId));
		shippingMethod.setPaymentMethods(new HashSet<>(paymentMethodService.findList(paymentMethodIds)));
		if (!isValid(shippingMethod)) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		shippingMethod.setAreaFreightConfigs(null);
		shippingMethod.setOrders(null);
		shippingMethodService.save(shippingMethod);
		return Results.OK;
	}

	/**
	 * 编辑
	 */
	@GetMapping("/edit")
	public String edit(Long id, ModelMap model) {
		model.addAttribute("deliveryCorps", deliveryCorpService.findAll());
		model.addAttribute("paymentMethods", paymentMethodService.findAll());
		model.addAttribute("shippingMethod", shippingMethodService.find(id));
		return "admin/shipping_method/edit";
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public ResponseEntity<?> update(ShippingMethod shippingMethod, Long defaultDeliveryCorpId, Long[] paymentMethodIds) {
		shippingMethod.setDefaultDeliveryCorp(deliveryCorpService.find(defaultDeliveryCorpId));
		shippingMethod.setPaymentMethods(new HashSet<>(paymentMethodService.findList(paymentMethodIds)));
		if (!isValid(shippingMethod)) {
			return Results.UNPROCESSABLE_ENTITY;
		}
		shippingMethodService.update(shippingMethod, "areaFreightConfigs", "orders");
		return Results.OK;
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", shippingMethodService.findPage(pageable));
		return "admin/shipping_method/list";
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public ResponseEntity<?> delete(Long[] ids) {
		if (ids.length >= shippingMethodService.count()) {
			return Results.unprocessableEntity("common.deleteAllNotAllowed");
		}
		shippingMethodService.delete(ids);
		return Results.OK;
	}

}