/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: ARfmzfm3xrogfPMn3mv/iyUAZaoplM35
 */
package com.igomall.controller.admin;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.igomall.Pageable;
import com.igomall.Results;
import com.igomall.service.OrderShippingService;

/**
 * Controller - 订单发货
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminOrderShippingController")
@RequestMapping("/admin/order_shipping")
public class OrderShippingController extends BaseController {

	@Resource
	private OrderShippingService orderShippingService;

	/**
	 * 查看
	 */
	@GetMapping("/view")
	public String view(Long id, ModelMap model) {
		model.addAttribute("shipping", orderShippingService.find(id));
		return "admin/order_shipping/view";
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", orderShippingService.findPage(pageable));
		return "admin/order_shipping/list";
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public ResponseEntity<?> delete(Long[] ids) {
		orderShippingService.delete(ids);
		return Results.OK;
	}

}