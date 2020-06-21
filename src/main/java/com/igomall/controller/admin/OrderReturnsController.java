/*
 * Copyright 2008-2018 shopxx.net. All rights reserved.
 * Support: localhost
 * License: localhost/license
 * FileId: E/os45khICmChzUqrzbOkTaovI8Aj4dC
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
import com.igomall.service.OrderReturnsService;

/**
 * Controller - 订单退货
 * 
 * @author 好源++ Team
 * @version 6.1
 */
@Controller("adminOrderReturnsController")
@RequestMapping("/admin/order_returns")
public class OrderReturnsController extends BaseController {

	@Resource
	private OrderReturnsService orderReturnsService;

	/**
	 * 查看
	 */
	@GetMapping("/view")
	public String view(Long id, ModelMap model) {
		model.addAttribute("returns", orderReturnsService.find(id));
		return "admin/order_returns/view";
	}

	/**
	 * 列表
	 */
	@GetMapping("/list")
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", orderReturnsService.findPage(pageable));
		return "admin/order_returns/list";
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public ResponseEntity<?> delete(Long[] ids) {
		orderReturnsService.delete(ids);
		return Results.OK;
	}

}