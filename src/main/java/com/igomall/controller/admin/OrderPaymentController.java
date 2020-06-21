/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: mpCwLJEOPvknFSB29TkrDcOF1CqVV0Yj
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
import com.igomall.service.OrderPaymentService;

/**
 * Controller - 订单支付
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminOrderPaymentController")
@RequestMapping("/admin/order_payment")
public class OrderPaymentController extends BaseController {

	@Resource
	private OrderPaymentService orderPaymentService;

	/**
	 * 查看
	 */
	@GetMapping("/view")
	public String view(Long id, ModelMap model) {
		model.addAttribute("orderPayment", orderPaymentService.find(id));
		return "admin/order_payment/view";
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", orderPaymentService.findPage(pageable));
		return "admin/order_payment/list";
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public ResponseEntity<?> delete(Long[] ids) {
		orderPaymentService.delete(ids);
		return Results.OK;
	}

}